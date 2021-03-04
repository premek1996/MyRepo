package changehistorytrackers;

import domain.Commit;
import domain.InvestigatedSourceElement;
import gitapi.MethodModificationsApi;

import java.util.List;

public class MethodChangeHistoryTracker extends ChangeHistoryTracker {

    public MethodChangeHistoryTracker(InvestigatedSourceElement investigatedSourceElement) {
        super(investigatedSourceElement);
    }

    @Override
    public List<Commit> getCommits() {
        return MethodModificationsApi.getCommits(investigatedSourceElement);
    }

}
