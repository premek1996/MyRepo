package processmetrics;

import domain.Commit;
import domain.InvestigatedSourceElement;
import domain.Metric;

/*
The maximum number of lines of code added
with one commit to the source element.
 */

public class AddedLinesMaxNumber implements ProcessMetric {

    private static final String METRIC_NAME = "AddedLinesMaxNumber";

    @Override
    public Metric compute(InvestigatedSourceElement investigatedSourceElement) {
        int addedLinesMaxNumber = investigatedSourceElement.getCommits().stream()
                .mapToInt(Commit::getAddedLines)
                .max()
                .orElse(0);
        System.out.println("Max number of added lines: " + addedLinesMaxNumber);
        return new Metric(METRIC_NAME, addedLinesMaxNumber);
    }

    @Override
    public String getName() {
        return METRIC_NAME;
    }

}
