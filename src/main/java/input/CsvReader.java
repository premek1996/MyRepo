package input;

import domain.InvestigatedSourceElement;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CsvReader {

    private CsvReader() {
    }

    public static List<InvestigatedSourceElement> getInvestigatedSourceElementsFromCsvFile(String csvFilePath) {
        List<InvestigatedSourceElement> investigatedSourceElements = new ArrayList<>();
        try (Reader in = new FileReader(csvFilePath)) {
            Iterable<CSVRecord> records = CSVFormat.DEFAULT
                    .withHeader(CsvHeader.class).withSkipHeaderRecord().parse(in);
            records.forEach(record -> investigatedSourceElements.add(getInvestigatedSourceElementFroCsvRecord(record)));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return investigatedSourceElements;
    }

    private static InvestigatedSourceElement getInvestigatedSourceElementFroCsvRecord(CSVRecord csvRecord) {
        String type = csvRecord.get(CsvHeader.TYPE);
        String packageName = csvRecord.get(CsvHeader.PACKAGE);
        String outerClass = csvRecord.get(CsvHeader.OUTER_CLASS);
        String hash = csvRecord.get(CsvHeader.COMMIT_HASH);
        int startLine = Integer.parseInt(csvRecord.get(CsvHeader.START_LINE));
        int endLine = Integer.parseInt(csvRecord.get(CsvHeader.END_LINE));
        String className = csvRecord.get(CsvHeader.CLASS);
        String methodName = csvRecord.get(CsvHeader.METHOD);
        List<String> parameters = Arrays.asList(csvRecord.get(CsvHeader.PARAMETERS).split("\\|"));
        System.out.println(type);
        System.out.println(packageName);
        System.out.println(outerClass);
        System.out.println(hash);
        System.out.println(startLine);
        System.out.println(endLine);
        System.out.println(className);
        System.out.println(methodName);
        System.out.println(parameters);
        System.out.println();
        return null;
    }

}
