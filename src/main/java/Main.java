import domain.Developer;
import gitapi.DevelopersApi;
import historytrackers.ClassChangeHistoryTracker;

import java.io.IOException;
import java.util.List;


public class Main {

    private static final String repoPath = "C:\\Users\\przem\\java-metrics-source-repos\\apache\\syncope";
    private static final String filePath = "client/idrepo/ui/src/main/java/org/apache/syncope/client/ui/commons/ConnIdSpecialName.java";

    public static void main(String[] args) throws IOException, InterruptedException {
        ClassChangeHistoryTracker classHistoryTracker = new ClassChangeHistoryTracker(repoPath, filePath);

        List<String> commits = classHistoryTracker.getHashCommits();
        System.out.println("Commits: " + commits);

        List<Developer> developers = DevelopersApi.getDevelopers(repoPath);
        System.out.println(developers);

    }

}
