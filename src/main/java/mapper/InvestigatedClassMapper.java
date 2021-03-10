package mapper;

import domain.InvestigatedClass;
import input.CSVInputRow;

import java.io.File;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InvestigatedClassMapper {

    private static final String DEFAULT_OUTPUT_REPOSITORY_NAME = "unknown-repository";
    private static final String DEFAULT_OUTPUT_REPOSITORY_DIR = System.getProperty("user.home") + File.separator + "java-metrics-source-repos";
    private static final Pattern PATTERN = Pattern.compile(".*:(.*)\\.git");

    private InvestigatedClassMapper() {
    }

    public static InvestigatedClass from(CSVInputRow row) {
        return InvestigatedClass.builder()
                .withRepositoryURI(row.getRepositoryURI())
                .withClassName(row.getClassName())
                .withCurrentHashCommit(row.getCurrentHashCommit())
                .withRepositoryPath(getRepositoryPath(row.getRepositoryURI()))
                .withStartLine(row.getStartLine())
                .withEndLine(row.getEndLine())
                .withFilePath(getFilePath(row.getFilePath()))
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
