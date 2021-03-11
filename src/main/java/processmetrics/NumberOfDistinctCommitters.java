package processmetrics;

import domain.Commit;
import domain.InvestigatedSourceElement;
import domain.Metric;


/**
 * Name: Number of Distinct Committers
 * Description: The metric counts the number of distinct authors, usually developers,
 * who committed their changes in a given Java class/method during the development
 * of the investigated release of a software system.
 * Reference: Lech Madeyski and Marian Jureczko. 2015. Which process metrics can significantly improve defect prediction models?
 * An empirical study. Software Qual J23, 393–422
 */

public class NumberOfDistinctCommitters implements ProcessMetric {

    private static final String METRIC_NAME = "NumberOfDistinctCommitters";

    @Override
    public Metric compute(InvestigatedSourceElement investigatedSourceElement) {
        long numberOfDistinctCommitters = investigatedSourceElement.getCommits().stream()
                .map(Commit::getDeveloper)
                .distinct()
                .count();
        return new Metric(METRIC_NAME, numberOfDistinctCommitters);
    }

    @Override
    public String getName() {
        return METRIC_NAME;
    }

}
