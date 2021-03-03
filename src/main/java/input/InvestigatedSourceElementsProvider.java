package input;

import domain.InvestigatedClass;
import domain.InvestigatedMethod;
import domain.InvestigatedSourceElement;
import gitapi.CommitBasicInfoApi;

import java.io.File;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class InvestigatedSourceElementsProvider {

    private static final String DEFAULT_OUTPUT_REPOSITORY_NAME = "unknown-repository";
    private static final String DEFAULT_OUTPUT_REPOSITORY_DIR = System.getProperty("user.home") + File.separator + "java-metrics-source-repos";
    private static int DONE_ELEMENTS_NUMBER = 0;

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
        return rows.stream()
                .map(InvestigatedSourceElementsProvider::getInvestigatedSourceElement)
                .collect(Collectors.toList());
    }

    private static InvestigatedSourceElement getInvestigatedSourceElement(CSVInputRow row) {
        System.out.println(DONE_ELEMENTS_NUMBER);
        DONE_ELEMENTS_NUMBER += 1;
        if (isClass(row.getType())) {
            return InvestigatedClass.builder()
                    .withRepositoryUri(row.getRepositoryUri())
                    .withClassName(row.getClassName())
                    .withCurrentHashCommit(row.getCurrentHashCommit())
                    .withRepositoryPath(getRepositoryPath(row.getRepositoryUri()))
                    .withStartLine(row.getStartLine())
                    .withEndLine(row.getEndLine())
                    .withFilePath(getFilePath(row.getFilePath()))
                    .build();
        } else if (isMethodOrConstructor(row.getType())) {
            return InvestigatedMethod.builder()
                    .withRepositoryUri(row.getRepositoryUri())
                    .withClassName(row.getClassName())
                    .withCurrentHashCommit(row.getCurrentHashCommit())
                    .withRepositoryPath(getRepositoryPath(row.getRepositoryUri()))
                    .withStartLine(row.getStartLine())
                    .withEndLine(row.getEndLine())
                    .withFilePath(getFilePath(row.getFilePath()))
                    .withMethodName(row.getMethodName())
                    .withArguments(row.getParameters())
                    .build();
        } else {
            throw new RuntimeException("Found unknown value " + row.getType() + " in column 'type'!");
        }
    }

    private static boolean isClass(String type) {
        return type.equals(InvestigatedClass.CLASS_TYPE);
    }

    private static boolean isMethodOrConstructor(String type) {
        return type.equals(InvestigatedMethod.METHOD_TYPE) || type.equals(InvestigatedMethod.CONSTRUCTOR_TYPE);
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

    private static String getFilePath(String filePath) {
        return filePath.substring(1);
    }

}
