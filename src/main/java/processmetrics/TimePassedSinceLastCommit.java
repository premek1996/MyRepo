package processmetrics;

import domain.Commit;
import domain.InvestigatedSourceElement;

import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

/*
Time passed in days since the last commit.
 */

public class TimePassedSinceLastCommit implements ProcessMetric {

    private static final String METRIC_NAME = "TimePassedSinceLastCommit";

    public void compute(InvestigatedSourceElement investigatedSourceElement) {
        List<Commit> commits = investigatedSourceElement.getCommits();
        Date currentDate = investigatedSourceElement.getCurrentDate();
        Commit lastCommit = commits.get(0);
        Date lastCommitDate = lastCommit.getDate();
        long days = ChronoUnit.DAYS.between(lastCommitDate.toInstant(), currentDate.toInstant());
        System.out.println("Time passed in days since the last commit: " + days);
    }

    @Override
    public String getName() {
        return METRIC_NAME;
    }

}
