package processmetrics;

import domain.Commit;
import domain.InvestigatedSourceElement;
import domain.Metric;

/*
The number of days with at least one commit
in the assignment period.
 */

public class DaysWithCommits implements ProcessMetric<Long> {

    private static final String METRIC_NAME = "DaysWithCommits";

    @Override
    public Metric<Long> compute(InvestigatedSourceElement investigatedSourceElement) {
        long daysWithCommits = investigatedSourceElement.getCommits().stream()
                .map(Commit::getDate)
                .distinct()
                .count();
        System.out.println("Number of days with at least one commit: " + daysWithCommits);
        return new Metric<>(METRIC_NAME, daysWithCommits);
    }

    @Override
    public String getName() {
        return METRIC_NAME;
    }

}
