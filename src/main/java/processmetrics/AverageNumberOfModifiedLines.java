package processmetrics;

import domain.Commit;
import domain.InvestigatedSourceElement;
import domain.Metric;

/**
 * Name: Average Number of Modified Lines
 * Description: The average number of modified lines (which were
 * added or removed) in a given Java class/method.
 * Reference: Lech Madeyski and Marian Jureczko. 2015. Which process metrics can significantly improve defect prediction models?
 * An empirical study. Software Qual J23, 393–422
 */

public class AverageNumberOfModifiedLines implements ProcessMetric {

    private static final String METRIC_NAME = "AverageNumberOfModifiedLines";

    @Override
    public Metric compute(InvestigatedSourceElement investigatedSourceElement) {
        double averageNumberOfModifiedLines = investigatedSourceElement.getCommits().stream()
                .mapToInt(Commit::getModifiedLines)
                .average()
                .orElse(0);
        return new Metric(METRIC_NAME, averageNumberOfModifiedLines);
    }

    @Override
    public String getName() {
        return METRIC_NAME;
    }

}
