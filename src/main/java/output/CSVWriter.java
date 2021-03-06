package output;

import domain.InvestigatedSourceElement;
import domain.Metric;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CSVWriter {

    private CSVWriter() {
    }

    public static void writeCSV(String filePath, List<InvestigatedSourceElement> investigatedSourceElements) {
        try (FileWriter out = new FileWriter(filePath)) {
            CSVPrinter printer = CSVFormat.DEFAULT
                    .withHeader(CSVOutputHeader.class)
                    .print(out);
            investigatedSourceElements.forEach(investigatedSourceElement -> printInvestigatedSourceElement(printer, investigatedSourceElement));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static void printInvestigatedSourceElement(CSVPrinter printer, InvestigatedSourceElement investigatedSourceElements) {
        List<String> investigatedSourceElementInfo = getInvestigatedSourceElementInfo(investigatedSourceElements);
        List<String> metricsValues = getMetricsValues(investigatedSourceElements.getMetrics());
        List<String> allInfoToPrint = Stream.concat(investigatedSourceElementInfo.stream(), metricsValues.stream())
                .collect(Collectors.toList());
        try {
            printer.printRecord(allInfoToPrint);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<String> getInvestigatedSourceElementInfo(InvestigatedSourceElement investigatedSourceElement) {
        return List.of(investigatedSourceElement.getRepositoryURI(),
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
