import domain.Commit;
import historytrackers.ClassHistoryTracker;
import processmetrics.NumberOfDistinctCommitters;
import processmetrics.NumberOfRevisions;

import java.io.IOException;
import java.util.List;


public class Main {

    private static final String repoPath = "C:\\Users\\przem\\java-metrics-source-repos\\apache\\syncope";
    private static final String filePath = "client/idrepo/ui/src/main/java/org/apache/syncope/client/ui/commons/ConnIdSpecialName.java";

    public static void main(String[] args) throws IOException, InterruptedException {
        ClassHistoryTracker classHistoryTracker = new ClassHistoryTracker(repoPath, filePath);
        List<Commit> commits = classHistoryTracker.getCommits();
        System.out.println("Commits: " + commits);
        NumberOfRevisions.calculate(commits);
        NumberOfDistinctCommitters.calculate(commits);
    }

}
