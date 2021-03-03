package changehistorytrackers;

import domain.Commit;
import domain.CommitBasicInfo;
import domain.Developer;
import domain.InvestigatedSourceElement;
import domain.SourceElementModification;
import gitapi.CommitBasicInfoApi;

import java.util.List;
import java.util.stream.Collectors;

public abstract class ChangeHistoryTracker {

    protected final InvestigatedSourceElement investigatedSourceElement;

    public ChangeHistoryTracker(InvestigatedSourceElement investigatedSourceElement) {
        this.investigatedSourceElement = investigatedSourceElement;
    }

    public List<Commit> getCommits() {
        List<SourceElementModification> sourceElementModifications = getSourceElementModifications();
        return sourceElementModifications.stream()
                .map(this::getCommit)
                .collect(Collectors.toList());
    }

    protected abstract List<SourceElementModification> getSourceElementModifications();

    private Commit getCommit(SourceElementModification sourceElementModification) {
        CommitBasicInfo commitBasicInfo = CommitBasicInfoApi.getCommitBasicInfo(investigatedSourceElement.getRepositoryPath(),
                sourceElementModification.getHash());
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
