package processmetrics;

import domain.Commit;
import domain.Developer;

import java.util.List;

/*
The metric represents the average number of commits per developer.
 */

public class DeveloperCommitsAverageNumber {

    public static void calculate(List<Commit> commits) {
        double developerCommitsAverageNumber = commits.stream()
                .map(Commit::getDeveloper)
                .distinct()
                .mapToInt(Developer::getCommitsNumber)
                .average()
                .orElse(0);
        System.out.println("The average number of commits per developer: " + developerCommitsAverageNumber);
    }

}
