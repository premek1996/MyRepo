package processmetrics;

import domain.Commit;
import domain.InvestigatedSourceElement;
import domain.Metric;

/**
 * Name: Max Number of Modified Lines
 * Description: The maximum number of modified lines (which were
 * added or removed) in a given Java class/method.
 * Reference: Lech Madeyski and Marian Jureczko. 2015. Which process metrics can significantly improve defect prediction models?
 * An empirical study. Software Qual J23, 393–422
 */

public class MaxNumberOfModifiedLines implements ProcessMetric {

    private static final String METRIC_NAME = "MaxNumberOfModifiedLines";

    @Override
    public Metric compute(InvestigatedSourceElement investigatedSourceElement) {
        double maxNumberOfModifiedLines = investigatedSourceElement.getCommits().stream()
                .mapToInt(Commit::getModifiedLines)
                .max()
                .orElse(0);
        return new Metric(METRIC_NAME, maxNumberOfModifiedLines);
    }

    @Override
    public String getName() {
        return METRIC_NAME;
    }

}
