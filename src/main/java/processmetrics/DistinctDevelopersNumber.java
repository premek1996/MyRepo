package processmetrics;

import domain.Commit;

import java.util.List;

/*
The metric counts the number of distinct authors, usually developers,
who committed their changes in a given Java class/method during the development
of the investigated release of a software system.
 */

public class DistinctDevelopersNumber {

    public static void calculate(List<Commit> commits) {
        long distinctDevelopersNumber = commits.stream()
                .map(Commit::getDeveloper)
                .distinct()
                .count();
        System.out.println("Number of distinct developers: " + distinctDevelopersNumber);
    }

}
