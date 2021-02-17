package processmetrics;

import domain.Commit;
import domain.InvestigatedSourceElement;

/*
The average number of lines of code deleted
from the source element.
 */

public class DeletedLinesAverageNumber implements ProcessMetric {

    private static final String METRIC_NAME = "DeletedLinesAverageNumber";

    @Override
    public void compute(InvestigatedSourceElement investigatedSourceElement) {
        double deletedLinesAverageNumber = investigatedSourceElement.getCommits().stream()
                .mapToInt(Commit::getDeletedLines)
                .average()
                .orElse(0);
        System.out.println("Average number of deleted lines: " + deletedLinesAverageNumber);
    }

    @Override
    public String getName() {
        return METRIC_NAME;
    }

}
