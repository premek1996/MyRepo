package processmetrics;

import domain.Commit;
import domain.InvestigatedSourceElement;
import domain.Metric;

/*
The metric counts the number of distinct authors, usually developers,
who committed their changes in a given Java class/method during the development
of the investigated release of a software system.
 */

public class DistinctDevelopersNumber implements ProcessMetric {

    private static final String METRIC_NAME = "DistinctDevelopersNumber";

    @Override
    public Metric compute(InvestigatedSourceElement investigatedSourceElement) {
        long distinctDevelopersNumber = investigatedSourceElement.getCommits().stream()
                .map(Commit::getDeveloper)
                .distinct()
                .count();
        return new Metric(METRIC_NAME, distinctDevelopersNumber);
    }

    @Override
    public String getName() {
        return METRIC_NAME;
    }

}
