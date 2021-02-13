package processmetrics;

import domain.Commit;

import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

/*
The age of source element in days.
 */

public class Age {

    public static void calculate(List<Commit> commits, Date currentDate) {
        Commit creationCommit = commits.get(commits.size() - 1);
        Date creationDate = creationCommit.getDate();
        long days = ChronoUnit.DAYS.between(creationDate.toInstant(), currentDate.toInstant());
        System.out.println("Age of source element in days: " + days);
    }

}
