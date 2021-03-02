package processmetrics;

import domain.Commit;
import domain.InvestigatedSourceElement;
import domain.Metric;

/*
The average number of modified lines (which were
added or removed) in a given Java class/method.
 */

public class ModifiedLinesAverageNumber implements ProcessMetric {

    private static final String METRIC_NAME = "ModifiedLinesAverageNumber";

    @Override
    public Metric compute(InvestigatedSourceElement investigatedSourceElement) {
        double modifiedLinesAverageNumber = investigatedSourceElement.getCommits().stream()
                .mapToInt(Commit::getModifiedLines)
                .average()
                .orElse(0);
        System.out.println("Average number of modified lines: " + modifiedLinesAverageNumber);
        return new Metric(METRIC_NAME, modifiedLinesAverageNumber);
    }

    @Override
    public String getName() {
        return METRIC_NAME;
    }

}
