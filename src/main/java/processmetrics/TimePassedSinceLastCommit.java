package processmetrics;


import domain.Commit;

import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

/*
Time passed in days since the last commit.
 */

public class TimePassedSinceLastCommit {

    public static void calculate(List<Commit> commits, Date currentDate) {
        Commit lastCommit = commits.get(0);
        Date lastCommitDate = lastCommit.getDate();
        long days = ChronoUnit.DAYS.between(lastCommitDate.toInstant(), currentDate.toInstant());
        System.out.println("Time passed in days since the last commit: " + days);
    }

}
