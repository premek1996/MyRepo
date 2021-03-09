import domain.InvestigatedClass;
import domain.InvestigatedSourceElement;
import input.InvestigatedSourceElementsProvider;
import output.CSVWriter;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


public class Main {

    private static final String REPOSITORY_PATH = "C:\\Users\\przem\\java-metrics-source-repos\\apache\\syncope";
    private static final String FILE_PATH = "client/idrepo/ui/src/main/java/org/apache/syncope/client/ui/commons/ConnIdSpecialName.java";
    private static final String CURRENT_HASH_COMMIT = "114c412afbfba24ffb4fbc804e5308a823a16a78";

    /*private static final String REPOSITORY_PATH = "C:\\Users\\przem\\IdeaProjects\\javaparser";
    private static final String FILE_PATH = "src/main/java/Main.java";
    private static final String CURRENT_HASH_COMMIT = "9569b49";*/

    /*private static final String REPOSITORY_PATH = "C:\\Users\\przem\\java-metrics-source-repos\\apache\\tez";
    private static final String FILE_PATH = "tez-runtime-library/src/main/java/org/apache/tez/runtime/library/common/writers/UnorderedPartitionedKVWriter.java";
    private static final String CURRENT_HASH_COMMIT = "d5675c332497c1ac1dedefdf91e87476b5c0d7a9";*/

    private static final String CSV_INPUT_FILE_PATH = "C:\\Users\\przem\\OneDrive\\Pulpit\\csvinput\\input.csv";
    private static final String CSV_OUTPUT_FILE_PATH = "C:\\Users\\przem\\OneDrive\\Pulpit\\csvinput\\output.csv";

    public static void main(String[] args) throws IOException, InterruptedException {

        /*final InvestigatedSourceElement investigatedSourceElement = InvestigatedClass.builder()
                .withRepositoryPath(REPOSITORY_PATH)
                .withFilePath(FILE_PATH)
                .withStartLine(89)
                .withEndLine(1427)
                .withCurrentHashCommit(CURRENT_HASH_COMMIT)
                .build();

        System.out.println(ClassModificationsApi.getCommits(investigatedSourceElement));*/

        List<InvestigatedSourceElement> investigatedSourceElements = InvestigatedSourceElementsProvider.from(CSV_INPUT_FILE_PATH);

        List<InvestigatedSourceElement> investigatedClasses = investigatedSourceElements.stream()
                .filter(investigatedSourceElement -> investigatedSourceElement instanceof InvestigatedClass)
                .collect(Collectors.toList());

        CSVWriter.writeCSV(CSV_OUTPUT_FILE_PATH, investigatedClasses);

    }

}
