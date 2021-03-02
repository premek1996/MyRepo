package processmetrics;

import domain.Commit;
import domain.InvestigatedSourceElement;
import domain.Metric;

/*
The maximum number of lines of code deleted
with one commit from the source element.
 */

public class DeletedLinesMaxNumber implements ProcessMetric<Integer> {

    private static final String METRIC_NAME = "DeletedLinesMaxNumber";

    @Override
    public Metric<Integer> compute(InvestigatedSourceElement investigatedSourceElement) {
        int deletedLinesMaxNumber = investigatedSourceElement.getCommits().stream()
                .mapToInt(Commit::getDeletedLines)
                .max()
                .orElse(0);
        System.out.println("Max number of deleted lines: " + deletedLinesMaxNumber);
        return new Metric<>(METRIC_NAME, deletedLinesMaxNumber);
    }

    @Override
    public String getName() {
        return METRIC_NAME;
    }

}
