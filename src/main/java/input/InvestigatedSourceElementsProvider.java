package input;

import com.google.common.collect.Lists;
import domain.InvestigatedSourceElement;
import gitapi.CommitBasicInfoApi;

import java.io.File;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class InvestigatedSourceElementsProvider {

    private static final String DEFAULT_OUTPUT_REPOSITORY_NAME = "unknown-repository";
    private static final String DEFAULT_OUTPUT_REPOSITORY_DIR = System.getProperty("user.home") + File.separator + "java-metrics-source-repos";

    private InvestigatedSourceElementsProvider() {
    }

    public static List<InvestigatedSourceElement> getInvestigatedSourceElements(String csvFilePath) {
        List<CSVInputRow> rows = CSVReader.getRows(csvFilePath);
        System.out.println(rows.size());
        List<CSVInputRow> rowsWithAvailableCommits = getRowsWithAvailableCommits(rows);
        System.out.println(rowsWithAvailableCommits.size());
        return getInvestigatedSourceElements(rowsWithAvailableCommits);
    }

    private static List<CSVInputRow> getRowsWithAvailableCommits(List<CSVInputRow> rows) {
        return rows.stream()
                .filter(InvestigatedSourceElementsProvider::hasAvailableCommit)
                .collect(Collectors.toList());
    }

    private static boolean hasAvailableCommit(CSVInputRow row) {
        return CommitBasicInfoApi.isCommitAvailable(getRepositoryPath(row.getRepositoryUri()), row.getCurrentHashCommit());
    }

    private static List<InvestigatedSourceElement> getInvestigatedSourceElements(List<CSVInputRow> rows) {
        int threadsNumber = Runtime.getRuntime().availableProcessors();
        List<List<CSVInputRow>> rowsSubSets = divideRowsIntoSubSets(rows, threadsNumber);
        ExecutorService executorService = Executors.newFixedThreadPool(threadsNumber);

        List<Supplier<List<InvestigatedSourceElement>>> getInvestigatedSourceElementsTasks
                = rowsSubSets.stream().map(GetInvestigatedSourceElementsTask::new)
                .collect(Collectors.toList());

        List<CompletableFuture<List<InvestigatedSourceElement>>> futureLists = getInvestigatedSourceElementsTasks.stream()
                .map(getInvestigatedSourceElementsTask -> CompletableFuture.supplyAsync(getInvestigatedSourceElementsTask, executorService))
                .collect(Collectors.toList());

        List<InvestigatedSourceElement> investigatedSourceElements = futureLists.stream()
                .map(CompletableFuture::join)
                .flatMap(List::stream)
                .collect(Collectors.toList());

        return investigatedSourceElements;
    }

    private static List<List<CSVInputRow>> divideRowsIntoSubSets(List<CSVInputRow> rows, int threadsNumber) {
        int rowsSubSetSize = getRowsSubSetSize(rows, threadsNumber);
        return Lists.partition(rows, rowsSubSetSize);
    }

    private static int getRowsSubSetSize(List<CSVInputRow> rows, int threadsNumber) {
        return (int) Math.ceil((double) rows.size() / (double) threadsNumber);
    }

    private static String getRepositoryPath(String repositoryUri) {
        return DEFAULT_OUTPUT_REPOSITORY_DIR + "\\" + getRepositoryName(repositoryUri);
    }

    private static String getRepositoryName(String repositoryUri) {
        Pattern pattern = Pattern.compile(".*:(.*)\\.git");
        Matcher matcher = pattern.matcher(repositoryUri);
        if (matcher.find()) {
            return matcher.group(1);
        } else {
            return DEFAULT_OUTPUT_REPOSITORY_NAME;
        }
    }

}
