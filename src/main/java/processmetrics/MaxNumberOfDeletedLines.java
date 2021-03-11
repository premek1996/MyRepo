package processmetrics;

import domain.Commit;
import domain.InvestigatedSourceElement;
import domain.Metric;

/**
 * Name: Max Number of Deleted Lines
 * Description: The maximum number of lines of code deleted with one commit from the source element.
 * Reference: Péter Gyimesi. 2017. Automatic calculation of process metrics and their bug
 * prediction capabilities. Acta Cybernetica, 23(2), 537-559
 */

public class MaxNumberOfDeletedLines implements ProcessMetric {

    private static final String METRIC_NAME = "MaxNumberOfDeletedLines";

    @Override
    public Metric compute(InvestigatedSourceElement investigatedSourceElement) {
        int maxNumberOfDeletedLines = investigatedSourceElement.getCommits().stream()
                .mapToInt(Commit::getDeletedLines)
                .max()
                .orElse(0);
        return new Metric(METRIC_NAME, maxNumberOfDeletedLines);
    }

    @Override
    public String getName() {
        return METRIC_NAME;
    }

}
