package processmetrics;

import domain.Commit;
import domain.InvestigatedSourceElement;
import domain.Metric;

/**
 * Name: Days With Commits
 * Description: The number of days with at least one commit in the assignment period.
 * Reference: Linus W. Dietz, Robin Lichtenthälery, Adam Tornhillz and Simon Harrer.
 * 2019. Code Process Metrics in University Programming Education. Software
 * Engineering
 */

public class DaysWithCommits implements ProcessMetric {

    private static final String METRIC_NAME = "DaysWithCommits";

    @Override
    public Metric compute(InvestigatedSourceElement investigatedSourceElement) {
        long daysWithCommits = investigatedSourceElement.getCommits().stream()
                .map(Commit::getDate)
                .distinct()
                .count();
        return new Metric(METRIC_NAME, daysWithCommits);
    }

    @Override
    public String getName() {
        return METRIC_NAME;
    }

}
