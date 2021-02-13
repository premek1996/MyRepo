package processmetrics;

import domain.Commit;

import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

/*
The average number of days that passed between consecutive
modifications of the source element.
 */

public class AverageTimeBetweenCommits {

    public static void calculate(List<Commit> commits) {
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

}
