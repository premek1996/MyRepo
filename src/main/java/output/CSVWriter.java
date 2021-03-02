package output;

import domain.InvestigatedSourceElement;
import domain.Metric;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CSVWriter {

    private CSVWriter() {
    }

    public static void writeCsv(String filePath, List<InvestigatedSourceElement> investigatedSourceElements, List<List<Metric>> metricsList) {
        List<CSVOutputRow> rows = getRows(investigatedSourceElements, metricsList);
        try (FileWriter out = new FileWriter(filePath)) {
            CSVPrinter printer = CSVFormat.DEFAULT
                    .withHeader(CSVOutputHeader.class).print(out);
            rows.forEach(row -> printRow(printer, row));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static List<CSVOutputRow> getRows(List<InvestigatedSourceElement> investigatedSourceElements, List<List<Metric>> metricsList) {
        int rowsNumber = investigatedSourceElements.size();
        return Stream.iterate(0, index -> index < rowsNumber, index -> index + 1)
                .map(toRow(investigatedSourceElements, metricsList))
                .collect(Collectors.toList());
    }

    private static Function<Integer, CSVOutputRow> toRow(List<InvestigatedSourceElement> investigatedSourceElements, List<List<Metric>> metricsList) {
        return index -> new CSVOutputRow(investigatedSourceElements.get(index), metricsList.get(index));
    }

    private static void printRow(CSVPrinter printer, CSVOutputRow row) {
        List<String> investigatedSourceElementInfo = getInvestigatedSourceElementInfo(row.getInvestigatedSourceElement());
        List<String> metricsValues = getMetricsValues(row.getMetrics());
        List<String> allInfoToPrint = Stream.concat(investigatedSourceElementInfo.stream(), metricsValues.stream())
                .collect(Collectors.toList());
        try {
            printer.printRecord(allInfoToPrint);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static List<String> getInvestigatedSourceElementInfo(InvestigatedSourceElement investigatedSourceElement) {
        return List.of(investigatedSourceElement.getRepositoryUri(),
                investigatedSourceElement.getFilePath(),
                investigatedSourceElement.getClassName(),
                investigatedSourceElement.getMethodName());
    }

    private static List<String> getMetricsValues(List<Metric> metrics) {
        return metrics.stream()
                .map(Metric::getValue)
                .map(Number::toString)
                .collect(Collectors.toList());
    }

}
