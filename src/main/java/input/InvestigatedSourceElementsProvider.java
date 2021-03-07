package input;

import domain.InvestigatedSourceElement;
import gitapi.CommitBasicInfoApi;
import utils.ListDivider;

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
        return CommitBasicInfoApi.isCommitAvailable(getRepositoryPath(row.getRepositoryURI()), row.getCurrentHashCommit());
    }

    private static List<InvestigatedSourceElement> getInvestigatedSourceElements(List<CSVInputRow> rows) {
        int threadsNumber = Runtime.getRuntime().availableProcessors();
        ExecutorService executorService = Executors.newFixedThreadPool(threadsNumber);
        List<List<CSVInputRow>> rowsSubLists = ListDivider.divideListIntoSubLists(rows, threadsNumber);
        List<Supplier<List<InvestigatedSourceElement>>> investigatedSourceElementsSuppliers = getInvestigatedSourceElementsSuppliers(rowsSubLists);
        List<InvestigatedSourceElement> investigatedSourceElements = getInvestigatedSourceElements(investigatedSourceElementsSuppliers, executorService);
        executorService.shutdown();
        return investigatedSourceElements;
    }


    private static List<Supplier<List<InvestigatedSourceElement>>> getInvestigatedSourceElementsSuppliers(List<List<CSVInputRow>> rowsSubLists) {
        return rowsSubLists.stream()
                .map(GetInvestigatedSourceElementsTask::new)
                .collect(Collectors.toList());
    }

    private static List<InvestigatedSourceElement> getInvestigatedSourceElements(List<Supplier<List<InvestigatedSourceElement>>> investigatedSourceElementsSuppliers,
                                                                                 ExecutorService executorService) {
        List<CompletableFuture<List<InvestigatedSourceElement>>> futureInvestigatedSourceElements = investigatedSourceElementsSuppliers.stream()
                .map(investigatedSourceElementsSupplier -> CompletableFuture.supplyAsync(investigatedSourceElementsSupplier, executorService))
                .collect(Collectors.toList());
        return futureInvestigatedSourceElements.stream()
                .map(CompletableFuture::join)
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    private static String getRepositoryPath(String repositoryURI) {
        return DEFAULT_OUTPUT_REPOSITORY_DIR + "\\" + getRepositoryName(repositoryURI);
    }

    private static String getRepositoryName(String repositoryURI) {
        Pattern pattern = Pattern.compile(".*:(.*)\\.git");
        Matcher matcher = pattern.matcher(repositoryURI);
        if (matcher.find()) {
            return matcher.group(1);
        } else {
            return DEFAULT_OUTPUT_REPOSITORY_NAME;
        }
    }

}
