package processmetrics;

import domain.Commit;
import domain.InvestigatedSourceElement;
import domain.Metric;

/*
The average number of lines of code added
to the source element.
 */

public class AddedLinesAverageNumber implements ProcessMetric {

    private static final String METRIC_NAME = "AddedLinesAverageNumber";

    @Override
    public Metric compute(InvestigatedSourceElement investigatedSourceElement) {
        double addedLinesAverageNumber = investigatedSourceElement.getCommits().stream()
                .mapToInt(Commit::getAddedLines)
                .average()
                .orElse(0);
        System.out.println("Average number of added lines: " + addedLinesAverageNumber);
        return new Metric(METRIC_NAME, addedLinesAverageNumber);
    }

    @Override
    public String getName() {
        return METRIC_NAME;
    }

}
