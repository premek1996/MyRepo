package historytrackers;

import gitapi.HashCommitsApi;

import java.util.List;

public class ClassChangeHistoryTracker {

    private final String repoPath;
    private final String filePath;
    private final List<String> hashCommits;

    public ClassChangeHistoryTracker(String repoPath, String filePath) {
        this.repoPath = repoPath;
        this.filePath = filePath;
        this.hashCommits = HashCommitsApi.getHashCommits(repoPath, filePath);
    }

    public List<String> getHashCommits() {
        return this.hashCommits;
    }

}
