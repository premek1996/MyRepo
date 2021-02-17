package processmetrics;

import domain.Commit;
import domain.InvestigatedSourceElement;

/*
The maximum number of modified lines (which were
added or removed) in a given Java class/method.
 */

public class ModifiedLinesMaxNumber implements ProcessMetric {

    private static final String METRIC_NAME = "ModifiedLinesMaxNumber";

    @Override
    public void compute(InvestigatedSourceElement investigatedSourceElement) {
        double modifiedLinesMaxNumber = investigatedSourceElement.getCommits().stream()
                .mapToInt(Commit::getModifiedLines)
                .max()
                .orElse(0);
        System.out.println("Max number of modified lines: " + modifiedLinesMaxNumber);
    }

    @Override
    public String getName() {
        return METRIC_NAME;
    }

}
