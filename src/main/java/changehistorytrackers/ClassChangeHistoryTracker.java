package changehistorytrackers;

import domain.Commit;
import domain.CommitBasicInfo;
import domain.Developer;
import domain.InvestigatedSourceElement;
import domain.SourceElementModification;
import gitapi.ClassModificationsApi;
import gitapi.CommitBasicInfoApi;

import java.util.List;
import java.util.stream.Collectors;

public class ClassChangeHistoryTracker {

    private final InvestigatedSourceElement investigatedSourceElement;
    private final String repositoryPath;
    private final String filePath;

    public ClassChangeHistoryTracker(InvestigatedSourceElement investigatedSourceElement) {
        this.investigatedSourceElement = investigatedSourceElement;
        this.repositoryPath = investigatedSourceElement.getRepositoryPath();
        this.filePath = investigatedSourceElement.getFilePath();
    }

    public InvestigatedSourceElement getInvestigatedSourceElementWithSetCommits() {
        investigatedSourceElement.setCommits(getCommits());
        return investigatedSourceElement;
    }

    private List<Commit> getCommits() {
        List<SourceElementModification> sourceElementModifications =
                ClassModificationsApi.getSourceElementModifications(repositoryPath, filePath);
        return getCommits(sourceElementModifications);
    }

    private List<Commit> getCommits(List<SourceElementModification> sourceElementModifications) {
        return sourceElementModifications.stream()
                .map(this::getCommit)
                .collect(Collectors.toList());
    }

    private Commit getCommit(SourceElementModification sourceElementModification) {
        CommitBasicInfo commitBasicInfo = CommitBasicInfoApi.getCommitBasicInfo(repositoryPath, sourceElementModification.getHash());
        Developer developer = investigatedSourceElement.getDeveloper(commitBasicInfo.getMail());
        return Commit.builder()
                .withHash(sourceElementModification.getHash())
                .withAddedLines(sourceElementModification.getAddedLines())
                .withDeletedLines(sourceElementModification.getDeletedLines())
                .withDate(commitBasicInfo.getDate())
                .withMessage(commitBasicInfo.getMessage())
                .withDeveloper(developer)
                .build();
    }

}
