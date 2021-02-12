package changehistorytrackers;

import domain.Commit;
import domain.CommitBasicInfo;
import domain.Developer;
import domain.InvestigatedSourceElement;
import gitapi.CommitBasicInfoApi;
import gitapi.CommitsHashesApi;

import java.util.List;
import java.util.stream.Collectors;

public class ClassChangeHistoryTracker {

    private final InvestigatedSourceElement investigatedSourceElement;
    private final String repoPath;
    private final String filePath;
    private final List<Commit> commits;

    public ClassChangeHistoryTracker(InvestigatedSourceElement investigatedSourceElement) {
        this.investigatedSourceElement = investigatedSourceElement;
        this.repoPath = investigatedSourceElement.getRepoPath();
        this.filePath = investigatedSourceElement.getFilePath();
        this.commits = determineCommits();
    }

    public List<Commit> getCommits() {
        return commits;
    }

    private List<Commit> determineCommits() {
        List<String> commitsHashes = CommitsHashesApi.getCommitsHashesWhichChangedFile(repoPath, filePath);
        return mapCommitsHashesToCommits(commitsHashes);
    }

    private List<Commit> mapCommitsHashesToCommits(List<String> commitsHashes) {
        return commitsHashes.stream()
                .map(this::mapHashCommitToCommit)
                .collect(Collectors.toList());
    }

    private Commit mapHashCommitToCommit(String hashCommit) {
        CommitBasicInfo commitBasicInfo = CommitBasicInfoApi.getCommitBasicInfo(repoPath, hashCommit);
        Developer developer = investigatedSourceElement.getDeveloper(commitBasicInfo.getMail());
        return Commit.builder().build();
    }

}
