package processmetrics;

import domain.Commit;
import domain.InvestigatedSourceElement;
import domain.Metric;

/**
 * Name: Average Number of Added Lines
 * Description: The average number of lines of code added to the source element.
 * Reference: Péter Gyimesi. 2017. Automatic calculation of process metrics and their bug
 * prediction capabilities. Acta Cybernetica, 23(2), 537-559
 */

public class AverageNumberOfAddedLines implements ProcessMetric {

    private static final String METRIC_NAME = "AverageNumberOfAddedLines";

    @Override
    public Metric compute(InvestigatedSourceElement investigatedSourceElement) {
        double averageNumberOfAddedLines = investigatedSourceElement.getCommits().stream()
                .mapToInt(Commit::getAddedLines)
                .average()
                .orElse(0);
        return new Metric(METRIC_NAME, averageNumberOfAddedLines);
    }

    @Override
    public String getName() {
        return METRIC_NAME;
    }

}
