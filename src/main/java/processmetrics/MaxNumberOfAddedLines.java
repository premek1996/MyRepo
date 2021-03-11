package processmetrics;

import domain.Commit;
import domain.InvestigatedSourceElement;
import domain.Metric;

/**
 * Name: Max Number of Added Lines
 * Description: The maximum number of lines of code added with one commit to the source element.
 * Reference: Péter Gyimesi. 2017. Automatic calculation of process metrics and their bug
 * prediction capabilities. Acta Cybernetica, 23(2), 537-559
 */

public class MaxNumberOfAddedLines implements ProcessMetric {

    private static final String METRIC_NAME = "MaxNumberOfAddedLines";

    @Override
    public Metric compute(InvestigatedSourceElement investigatedSourceElement) {
        int maxNumberOfAddedLines = investigatedSourceElement.getCommits().stream()
                .mapToInt(Commit::getAddedLines)
                .max()
                .orElse(0);
        return new Metric(METRIC_NAME, maxNumberOfAddedLines);
    }

    @Override
    public String getName() {
        return METRIC_NAME;
    }

}
