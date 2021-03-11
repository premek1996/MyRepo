package processmetrics;

import domain.Commit;
import domain.InvestigatedSourceElement;
import domain.Metric;

/**
 * Name: Average Number of Deleted Lines
 * Description: The average number of lines of code deleted from the source element.
 * Reference: Péter Gyimesi. 2017. Automatic calculation of process metrics and their bug
 * prediction capabilities. Acta Cybernetica, 23(2), 537-559
 */

public class AverageNumberOfDeletedLines implements ProcessMetric {

    private static final String METRIC_NAME = "AverageNumberOfDeletedLines";

    @Override
    public Metric compute(InvestigatedSourceElement investigatedSourceElement) {
        double averageNumberOfDeletedLines = investigatedSourceElement.getCommits().stream()
                .mapToInt(Commit::getDeletedLines)
                .average()
                .orElse(0);
        return new Metric(METRIC_NAME, averageNumberOfDeletedLines);
    }

    @Override
    public String getName() {
        return METRIC_NAME;
    }

}
