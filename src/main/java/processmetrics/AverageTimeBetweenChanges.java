package processmetrics;

import domain.Commit;
import domain.InvestigatedSourceElement;
import domain.Metric;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * Name: Average Time Between Changes
 * Description: The average number of days that passed between consecutive
 * modifications of the source element.
 * Reference: Péter Gyimesi. 2017. Automatic calculation of process metrics and their bug
 * prediction capabilities. Acta Cybernetica, 23(2), 537-559
 */

public class AverageTimeBetweenChanges implements ProcessMetric {

    private static final String METRIC_NAME = "AverageTimeBetweenChanges";

    @Override
    public Metric compute(InvestigatedSourceElement investigatedSourceElement) {
        List<Commit> commits = investigatedSourceElement.getCommits();
        long sumTimesBetweenChanges = 0;
        for (int i = 0; i < commits.size() - 1; i++) {
            int j = i + 1;
            LocalDate first = commits.get(i).getDate();
            LocalDate second = commits.get(j).getDate();
            sumTimesBetweenChanges += ChronoUnit.DAYS.between(second, first);
        }
        long averageTimeBetweenChanges = sumTimesBetweenChanges / commits.size();
        return new Metric(METRIC_NAME, averageTimeBetweenChanges);
    }

    @Override
    public String getName() {
        return METRIC_NAME;
    }

}
