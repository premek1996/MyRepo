package processmetrics;

import domain.Commit;
import domain.InvestigatedSourceElement;
import domain.Metric;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

/*
The average number of days that passed between consecutive
modifications of the source element.
 */

public class AverageTimeBetweenCommits implements ProcessMetric<Long> {

    private static final String METRIC_NAME = "AverageTimeBetweenCommits";

    @Override
    public Metric<Long> compute(InvestigatedSourceElement investigatedSourceElement) {
        List<Commit> commits = investigatedSourceElement.getCommits();
        long sumTimesBetweenCommits = 0;
        for (int i = 0; i < commits.size() - 1; i++) {
            int j = i + 1;
            LocalDate first = commits.get(i).getDate();
            LocalDate second = commits.get(j).getDate();
            sumTimesBetweenCommits += ChronoUnit.DAYS.between(second, first);
        }
        long averageTimeBetweenCommits = sumTimesBetweenCommits / commits.size();
        System.out.println("Average time between commits in days: " + averageTimeBetweenCommits);
        return new Metric<>(METRIC_NAME, averageTimeBetweenCommits);
    }

    @Override
    public String getName() {
        return METRIC_NAME;
    }

}
