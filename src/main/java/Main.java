import gitapi.SourceElementModificationApi;

import java.io.IOException;


public class Main {

    private static final String repoPath = "C:\\Users\\przem\\java-metrics-source-repos\\apache\\syncope";
    private static final String filePath = "client/idrepo/ui/src/main/java/org/apache/syncope/client/ui/commons/ConnIdSpecialName.java";
    private static final String currentHashCommit = "114c412afbfba24ffb4fbc804e5308a823a16a78";

    public static void main(String[] args) throws IOException, InterruptedException {

        /*InvestigatedSourceElement investigatedSourceElement = InvestigatedSourceElement.builder()
                .withRepoPath(repoPath)
                .withFilePath(filePath)
                .withCurrentHashCommit(currentHashCommit)
                .build();*/

        //ClassChangeHistoryTracker classHistoryTracker =
        // new ClassChangeHistoryTracker(investigatedSourceElement);

        SourceElementModificationApi.getInvestigatedSourceElementModification
                (repoPath, filePath, "c8e5eb5b16");


    }

}
