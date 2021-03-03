package changehistorytrackers;

import domain.InvestigatedSourceElement;
import domain.SourceElementModification;
import gitapi.ClassModificationsApi;

import java.util.List;

public class ClassChangeHistoryTracker extends ChangeHistoryTracker {

    public ClassChangeHistoryTracker(InvestigatedSourceElement investigatedSourceElement) {
        super(investigatedSourceElement);
    }

    @Override
    protected List<SourceElementModification> getSourceElementModifications() {
        return ClassModificationsApi.getSourceElementModifications(investigatedSourceElement);
    }

}
