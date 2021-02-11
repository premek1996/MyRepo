package historytrackers;

import domain.Commit;
import gitapi.CommitDateApi;
import gitapi.CommitsHashesApi;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ClassChangeHistoryTracker {

    private final String repoPath;
    private final String filePath;
    private final String currentHashCommit;
    private final Date currentDate;
    private final List<Commit> commits;

    public ClassChangeHistoryTracker(String repoPath,
                                     String filePath,
                                     String currentHashCommit) {
        this.repoPath = repoPath;
        this.filePath = filePath;
        this.currentHashCommit = currentHashCommit;
        this.currentDate = determineCurrentDate();
        this.commits = determineCommits();
    }

    public List<Commit> getCommits() {
        return commits;
    }

    public Date getCurrentDate() {
        return currentDate;
    }

    private Date determineCurrentDate() {
        return CommitDateApi.getCommitDate(repoPath, currentHashCommit);
    }

    private List<Commit> determineCommits() {
        List<String> commitsHashes = CommitsHashesApi.getCommitsHashesWhichChangedFile(repoPath, filePath);
        System.out.println(commitsHashes);
        return new ArrayList<>();
    }

}
