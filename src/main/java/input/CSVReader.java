package input;

import domain.InvestigatedClass;
import domain.InvestigatedMethod;
import domain.InvestigatedSourceElement;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CSVReader {

    private static final String DEFAULT_OUTPUT_REPOSITORY_NAME = "unknown-repository";
    private static final String DEFAULT_OUTPUT_REPOSITORY_DIR = System.getProperty("user.home") + File.separator + "java-metrics-source-repos";
    private static int DONE_ELEMENTS_NUMBER = 0;

    private CSVReader() {
    }

    public static List<InvestigatedSourceElement> getInvestigatedSourceElementsFromCsvFile(String csvFilePath) {
        List<InvestigatedSourceElement> investigatedSourceElements = new ArrayList<>();
        try (Reader in = new FileReader(csvFilePath)) {
            Iterable<CSVRecord> records = CSVFormat.DEFAULT
                    .withHeader(CSVInputHeader.class).withSkipHeaderRecord().parse(in);
            records.forEach(record -> investigatedSourceElements.add(getInvestigatedSourceElementFroCsvRecord(record)));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return investigatedSourceElements;
    }

    private static InvestigatedSourceElement getInvestigatedSourceElementFroCsvRecord(CSVRecord csvRecord) {
        String type = csvRecord.get(CSVInputHeader.TYPE);
        String repositoryUri = csvRecord.get(CSVInputHeader.REPOSITORY);
        String currentHashCommit = csvRecord.get(CSVInputHeader.COMMIT_HASH);
        int startLine = Integer.parseInt(csvRecord.get(CSVInputHeader.START_LINE));
        int endLine = Integer.parseInt(csvRecord.get(CSVInputHeader.END_LINE));
        String filePath = csvRecord.get(CSVInputHeader.PATH);
        String className = csvRecord.get(CSVInputHeader.CLASS);
        String methodName = csvRecord.get(CSVInputHeader.METHOD);
        List<String> parameters = Arrays.asList(csvRecord.get(CSVInputHeader.PARAMETERS).split("\\|"));
        DONE_ELEMENTS_NUMBER+=1;
        System.out.println(DONE_ELEMENTS_NUMBER);
        if (isClass(type)) {
            return InvestigatedClass.builder()
                    .withRepositoryUri(repositoryUri)
                    .withClassName(className)
                    .withCurrentHashCommit(currentHashCommit)
                    .withRepositoryPath(getRepositoryPath(repositoryUri))
                    .withStartLine(startLine)
                    .withEndLine(endLine)
                    .withFilePath(getFilePath(filePath))
                    .build();
        } else if (isMethodOrConstructor(type)) {
            return InvestigatedMethod.builder()
                    .withRepositoryUri(repositoryUri)
                    .withClassName(className)
                    .withCurrentHashCommit(currentHashCommit)
                    .withRepositoryPath(getRepositoryPath(repositoryUri))
                    .withStartLine(startLine)
                    .withEndLine(endLine)
                    .withFilePath(getFilePath(filePath))
                    .withMethodName(methodName)
                    .withArguments(parameters)
                    .build();
        } else {
            throw new RuntimeException("Found unknown value " + type + " in column 'type'!");
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
