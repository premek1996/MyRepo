package processmetrics;

import domain.Commit;
import domain.InvestigatedSourceElement;
import domain.Metric;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

/*
The age of source element in days.
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
        System.out.println("Age of source element in days: " + days);
        return new Metric(METRIC_NAME, days);
    }

    @Override
    public String getName() {
        return METRIC_NAME;
    }

}
