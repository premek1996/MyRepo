package processmetrics;

import domain.Commit;
import domain.InvestigatedSourceElement;

import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

/*
The average number of days that passed between consecutive
modifications of the source element.
 */

public class AverageTimeBetweenCommits implements ProcessMetric {

    private static final String METRIC_NAME = "AverageTimeBetweenCommits";

    @Override
    public void compute(InvestigatedSourceElement investigatedSourceElement) {
        List<Commit> commits = investigatedSourceElement.getCommits();
        long sumTimesBetweenCommits = 0;
        for (int i = 0; i < commits.size() - 1; i++) {
            int j = i + 1;
            Date first = commits.get(i).getDate();
            Date second = commits.get(j).getDate();
            sumTimesBetweenCommits += ChronoUnit.DAYS.between(second.toInstant(), first.toInstant());
        }
        long averageTimeBetweenCommits = sumTimesBetweenCommits / commits.size();
        System.out.println("Average time between commits in days: " + averageTimeBetweenCommits);
    }

    @Override
    public String getName() {
        return METRIC_NAME;
    }

}
