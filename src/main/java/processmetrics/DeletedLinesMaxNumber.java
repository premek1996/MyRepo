package processmetrics;

import domain.Commit;
import domain.InvestigatedSourceElement;

/*
The maximum number of lines of code deleted
with one commit from the source element.
 */

public class DeletedLinesMaxNumber implements ProcessMetric {

    private static final String METRIC_NAME = "DeletedLinesMaxNumber";

    @Override
    public void compute(InvestigatedSourceElement investigatedSourceElement) {
        int deletedLinesMaxNumber = investigatedSourceElement.getCommits().stream()
                .mapToInt(Commit::getDeletedLines)
                .max()
                .orElse(0);
        System.out.println("Max number of deleted lines: " + deletedLinesMaxNumber);
    }

    @Override
    public String getName() {
        return METRIC_NAME;
    }

}
