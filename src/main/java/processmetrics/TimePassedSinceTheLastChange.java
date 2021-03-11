package processmetrics;

import domain.Commit;
import domain.InvestigatedSourceElement;
import domain.Metric;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * Name: Time Passed Since the Last Change
 * Description: Time passed in days since the last commit.
 * Reference: Péter Gyimesi. 2017. Automatic calculation of process metrics and their bug
 * prediction capabilities. Acta Cybernetica, 23(2), 537-559
 */

public class TimePassedSinceTheLastChange implements ProcessMetric {

    private static final String METRIC_NAME = "TimePassedSinceTheLastChange";

    @Override
    public Metric compute(InvestigatedSourceElement investigatedSourceElement) {
        List<Commit> commits = investigatedSourceElement.getCommits();
        LocalDate currentDate = investigatedSourceElement.getCurrentDate();
        Commit lastCommit = commits.get(0);
        LocalDate lastCommitDate = lastCommit.getDate();
        long days = ChronoUnit.DAYS.between(lastCommitDate, currentDate);
        return new Metric(METRIC_NAME, days);
    }

    @Override
    public String getName() {
        return METRIC_NAME;
    }

}
