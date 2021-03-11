package processmetrics;

import domain.InvestigatedSourceElement;
import domain.Metric;

/**
 * Name: Number of Revisions
 * Description: The metric represents the number of revisions of a given
 * Java class/method during development of the investigated
 * release of a software system.
 * Reference: Lech Madeyski and Marian Jureczko. 2015. Which process metrics can significantly improve defect prediction models?
 * An empirical study. Software Qual J23, 393–422
 */

public class NumberOfRevisions implements ProcessMetric {

    private static final String METRIC_NAME = "NumberOfRevisions";

    @Override
    public Metric compute(InvestigatedSourceElement investigatedSourceElement) {
        int numberOfRevisions = investigatedSourceElement.getCommits().size();
        return new Metric(METRIC_NAME, numberOfRevisions);
    }

    @Override
    public String getName() {
        return METRIC_NAME;
    }

}
