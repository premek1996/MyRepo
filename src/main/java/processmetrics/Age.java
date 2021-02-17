package processmetrics;

import domain.Commit;
import domain.InvestigatedSourceElement;

import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

/*
The age of source element in days.
 */

public class Age implements ProcessMetric {

    private static final String METRIC_NAME = "Age";

    @Override
    public void compute(InvestigatedSourceElement investigatedSourceElement) {
        Date currentDate = investigatedSourceElement.getCurrentDate();
        List<Commit> commits = investigatedSourceElement.getCommits();
        Commit creationCommit = commits.get(commits.size() - 1);
        Date creationDate = creationCommit.getDate();
        long days = ChronoUnit.DAYS.between(creationDate.toInstant(), currentDate.toInstant());
        System.out.println("Age of source element in days: " + days);
    }

    @Override
    public String getName() {
        return METRIC_NAME;
    }

}
