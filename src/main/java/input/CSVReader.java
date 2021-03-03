package input;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVReader {

    private CSVReader() {
    }

    public static List<CSVInputRow> getRows(String csvFilePath) {
        List<CSVInputRow> rows = new ArrayList<>();
        try (Reader in = new FileReader(csvFilePath)) {
            Iterable<CSVRecord> records = CSVFormat.DEFAULT
                    .withHeader(CSVInputHeader.class).withSkipHeaderRecord().parse(in);
            records.forEach(record -> rows.add(getRow(record)));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return rows;
    }

    private static CSVInputRow getRow(CSVRecord csvRecord) {
        String type = csvRecord.get(CSVInputHeader.TYPE);
        String repositoryUri = csvRecord.get(CSVInputHeader.REPOSITORY);
        String currentHashCommit = csvRecord.get(CSVInputHeader.COMMIT_HASH);
        int startLine = Integer.parseInt(csvRecord.get(CSVInputHeader.START_LINE));
        int endLine = Integer.parseInt(csvRecord.get(CSVInputHeader.END_LINE));
        String filePath = csvRecord.get(CSVInputHeader.PATH);
        String className = csvRecord.get(CSVInputHeader.CLASS);
        String methodName = csvRecord.get(CSVInputHeader.METHOD);
        List<String> parameters = Arrays.asList(csvRecord.get(CSVInputHeader.PARAMETERS).split("\\|"));

        return CSVInputRow.builder()
                .withType(type)
                .withRepositoryUri(repositoryUri)
                .withCurrentHashCommit(currentHashCommit)
                .withStartLine(startLine)
                .withEndLine(endLine)
                .withFilePath(filePath)
                .withClassName(className)
                .withMethodName(methodName)
                .withParameters(parameters)
                .build();
    }

}
