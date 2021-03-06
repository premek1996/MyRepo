package input;

import domain.InvestigatedClass;
import domain.InvestigatedMethod;
import domain.InvestigatedSourceElement;

import java.io.File;
import java.util.List;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class GetInvestigatedSourceElementsTask implements Supplier<List<InvestigatedSourceElement>> {

    private static final String DEFAULT_OUTPUT_REPOSITORY_NAME = "unknown-repository";
    private static final String DEFAULT_OUTPUT_REPOSITORY_DIR = System.getProperty("user.home") + File.separator + "java-metrics-source-repos";

    private final List<CSVInputRow> rows;
    int count;

    public GetInvestigatedSourceElementsTask(List<CSVInputRow> rows) {
        this.rows = rows;
    }

    @Override
    public List<InvestigatedSourceElement> get() {
        return rows.stream()
                .map(this::getInvestigatedSourceElement)
                .collect(Collectors.toList());
    }

    private InvestigatedSourceElement getInvestigatedSourceElement(CSVInputRow row) {
        count++;
        System.out.println(Thread.currentThread().getName()+" "+count);
        if (isClass(row.getType())) {
            return InvestigatedClass.builder()
                    .withRepositoryURI(row.getRepositoryURI())
                    .withClassName(row.getClassName())
                    .withCurrentHashCommit(row.getCurrentHashCommit())
                    .withRepositoryPath(getRepositoryPath(row.getRepositoryURI()))
                    .withStartLine(row.getStartLine())
                    .withEndLine(row.getEndLine())
                    .withFilePath(getFilePath(row.getFilePath()))
                    .build();
        } else if (isMethodOrConstructor(row.getType())) {
            return InvestigatedMethod.builder()
                    .withRepositoryURI(row.getRepositoryURI())
                    .withClassName(row.getClassName())
                    .withCurrentHashCommit(row.getCurrentHashCommit())
                    .withRepositoryPath(getRepositoryPath(row.getRepositoryURI()))
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

    private static String getFilePath(String filePath) {
        return filePath.substring(1);
    }

}
