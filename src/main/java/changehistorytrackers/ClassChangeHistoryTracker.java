package changehistorytrackers;

import domain.Commit;
import domain.InvestigatedSourceElement;
import domain.SourceElementModification;
import gitapi.ClassModificationsApi;

import java.util.List;

public class ClassChangeHistoryTracker extends ChangeHistoryTracker {

    public ClassChangeHistoryTracker(InvestigatedSourceElement investigatedSourceElement) {
        super(investigatedSourceElement);
    }

    @Override
    public List<Commit> getCommits() {
        return ClassModificationsApi.getCommits(investigatedSourceElement);
    }

}
