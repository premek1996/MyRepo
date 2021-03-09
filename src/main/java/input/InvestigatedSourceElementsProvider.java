package input;

import domain.InvestigatedSourceElement;
import gitapi.CommitBasicInfoApi;
import utils.ListDivider;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class InvestigatedSourceElementsProvider {

    private static final String ERROR_TXT = "C:\\Users\\przem\\OneDrive\\Pulpit\\csvinput\\error.txt";
    private static final int THREADS_NUMBER = Runtime.getRuntime().availableProcessors();
    private static ExecutorService executorService;

    private InvestigatedSourceElementsProvider() {
    }

    public static List<InvestigatedSourceElement> from(String csvFilePath) throws IOException {
        List<CSVInputRow> rows = CSVReader.getRowsWithAvailableCommits(csvFilePath);
        writeErrors();
        return mapRowsToInvestigatedSourceElements(rows);
    }

    private static void writeErrors() throws IOException {
        FileWriter writer = new FileWriter(ERROR_TXT);
        for (String str : CommitBasicInfoApi.errors) {
            writer.write(str + System.lineSeparator());
        }
        writer.close();
    }

    private static List<InvestigatedSourceElement> mapRowsToInvestigatedSourceElements(List<CSVInputRow> rows) {
        executorService = Executors.newFixedThreadPool(THREADS_NUMBER);
        List<List<CSVInputRow>> rowsSubLists = ListDivider.divideListIntoSubLists(rows, THREADS_NUMBER);
        List<Supplier<List<InvestigatedSourceElement>>> investigatedSourceElementsSuppliers = getInvestigatedSourceElementsSuppliers(rowsSubLists);
        List<InvestigatedSourceElement> investigatedSourceElements = getInvestigatedSourceElements(investigatedSourceElementsSuppliers);
        executorService.shutdown();
        return investigatedSourceElements;
    }

    private static List<Supplier<List<InvestigatedSourceElement>>> getInvestigatedSourceElementsSuppliers(List<List<CSVInputRow>> rowsSubLists) {
        return rowsSubLists.stream()
                .map(GetInvestigatedSourceElementsTask::new)
                .collect(Collectors.toList());
    }

    private static List<InvestigatedSourceElement> getInvestigatedSourceElements(List<Supplier<List<InvestigatedSourceElement>>> investigatedSourceElementsSuppliers) {
        List<CompletableFuture<List<InvestigatedSourceElement>>> futureInvestigatedSourceElements = investigatedSourceElementsSuppliers.stream()
                .map(investigatedSourceElementsSupplier -> CompletableFuture.supplyAsync(investigatedSourceElementsSupplier, executorService))
                .collect(Collectors.toList());
        return futureInvestigatedSourceElements.stream()
                .map(CompletableFuture::join)
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

}
