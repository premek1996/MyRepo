package processmetrics;

import domain.Commit;

import java.util.List;

/*
The number of days with at least one commit
in the assignment period.
 */

public class DaysWithCommits {

    public static void calculate(List<Commit> commits) {
        long daysWithCommits = commits.stream()
                .map(Commit::getDate)
                .distinct()
                .count();
        System.out.println("Number of days with at least one commit: " + daysWithCommits);
    }

}
