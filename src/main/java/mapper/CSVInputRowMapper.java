package mapper;

import input.CSVInputHeader;
import input.CSVInputRow;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CSVInputRowMapper {

    private static final String DEFAULT_OUTPUT_REPOSITORY_NAME = "unknown-repository";
    private static final String DEFAULT_OUTPUT_REPOSITORY_DIR = System.getProperty("user.home") + File.separator + "java-metrics-source-repos";
    private static final Pattern PATTERN = Pattern.compile(".*:(.*)\\.git");

    private CSVInputRowMapper() {
    }

    public static CSVInputRow from(CSVRecord csvRecord) {
        String type = csvRecord.get(CSVInputHeader.TYPE);
        String repositoryURI = csvRecord.get(CSVInputHeader.REPOSITORY);
        String repositoryPath = getRepositoryPath(repositoryURI);
        String currentHashCommit = csvRecord.get(CSVInputHeader.COMMIT_HASH);
        int startLine = Integer.parseInt(csvRecord.get(CSVInputHeader.START_LINE));
        int endLine = Integer.parseInt(csvRecord.get(CSVInputHeader.END_LINE));
        String filePath = getFilePath(csvRecord.get(CSVInputHeader.PATH));
        String className = csvRecord.get(CSVInputHeader.CLASS);
        String methodName = csvRecord.get(CSVInputHeader.METHOD);
        List<String> parameters = Arrays.asList(csvRecord.get(CSVInputHeader.PARAMETERS).split("\\|"));

        return CSVInputRow.builder()
                .withType(type)
                .withRepositoryURI(repositoryURI)
                .withRepositoryPath(repositoryPath)
                .withCurrentHashCommit(currentHashCommit)
                .withStartLine(startLine)
                .withEndLine(endLine)
                .withFilePath(filePath)
                .withClassName(className)
                .withMethodName(methodName)
                .withParameters(parameters)
                .build();
    }

    private static String getRepositoryPath(String repositoryURI) {
        return DEFAULT_OUTPUT_REPOSITORY_DIR + "\\" + getRepositoryName(repositoryURI);
    }

    private static String getRepositoryName(String repositoryURI) {
        return Optional.of(PATTERN.matcher(repositoryURI))
                .filter(Matcher::find)
                .map(matcher -> matcher.group(1))
                .orElse(DEFAULT_OUTPUT_REPOSITORY_NAME);
    }

    private static String getFilePath(String filePath) {
        return filePath.substring(1);
    }

}
