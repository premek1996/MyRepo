package processmetrics;

import domain.Commit;
import domain.InvestigatedSourceElement;
import domain.Metric;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * Name: Age
 * Description: The age of source element in days.
 * Reference: Péter Gyimesi. 2017. Automatic calculation of process metrics and their bug
 * prediction capabilities. Acta Cybernetica, 23(2), 537-559
 */

public class Age implements ProcessMetric {

    private static final String METRIC_NAME = "Age";

    @Override
    public Metric compute(InvestigatedSourceElement investigatedSourceElement) {
        LocalDate currentDate = investigatedSourceElement.getCurrentDate();
        List<Commit> commits = investigatedSourceElement.getCommits();
        Commit creationCommit = commits.get(commits.size() - 1);
        LocalDate creationDate = creationCommit.getDate();
        long days = ChronoUnit.DAYS.between(creationDate, currentDate);
        return new Metric(METRIC_NAME, days);
    }

    @Override
    public String getName() {
        return METRIC_NAME;
    }

}
