package mapper;

import input.CSVInputHeader;
import input.CSVInputRow;
import org.apache.commons.csv.CSVRecord;

import java.util.Arrays;
import java.util.List;

public class CSVInputRowMapper {

    private CSVInputRowMapper() {
    }

    public static CSVInputRow from(CSVRecord csvRecord) {
        String type = csvRecord.get(CSVInputHeader.TYPE);
        String repositoryURI = csvRecord.get(CSVInputHeader.REPOSITORY);
        String currentHashCommit = csvRecord.get(CSVInputHeader.COMMIT_HASH);
        int startLine = Integer.parseInt(csvRecord.get(CSVInputHeader.START_LINE));
        int endLine = Integer.parseInt(csvRecord.get(CSVInputHeader.END_LINE));
        String filePath = csvRecord.get(CSVInputHeader.PATH);
        String className = csvRecord.get(CSVInputHeader.CLASS);
        String methodName = csvRecord.get(CSVInputHeader.METHOD);
        List<String> parameters = Arrays.asList(csvRecord.get(CSVInputHeader.PARAMETERS).split("\\|"));

        return CSVInputRow.builder()
                .withType(type)
                .withRepositoryURI(repositoryURI)
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
