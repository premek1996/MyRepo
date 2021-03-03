package changehistorytrackers;

import domain.InvestigatedSourceElement;
import domain.SourceElementModification;
import gitapi.MethodModificationsApi;

import java.util.List;

public class MethodChangeHistoryTracker extends ChangeHistoryTracker {

    public MethodChangeHistoryTracker(InvestigatedSourceElement investigatedSourceElement) {
        super(investigatedSourceElement);
    }

    @Override
    protected List<SourceElementModification> getSourceElementModifications() {
        return MethodModificationsApi.getSourceElementModifications(investigatedSourceElement);
    }

}
