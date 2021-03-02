package processmetrics;

import domain.Commit;
import domain.InvestigatedSourceElement;
import domain.Metric;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

/*
Time passed in days since the last commit.
 */

public class TimePassedSinceLastCommit implements ProcessMetric {

    private static final String METRIC_NAME = "TimePassedSinceLastCommit";

    @Override
    public Metric compute(InvestigatedSourceElement investigatedSourceElement) {
        List<Commit> commits = investigatedSourceElement.getCommits();
        LocalDate currentDate = investigatedSourceElement.getCurrentDate();
        Commit lastCommit = commits.get(0);
        LocalDate lastCommitDate = lastCommit.getDate();
        long days = ChronoUnit.DAYS.between(lastCommitDate, currentDate);
        System.out.println("Time passed in days since the last commit: " + days);
        return new Metric(METRIC_NAME, days);
    }

    @Override
    public String getName() {
        return METRIC_NAME;
    }

}
