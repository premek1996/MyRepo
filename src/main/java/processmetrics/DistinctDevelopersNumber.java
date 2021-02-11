package processmetrics;

import domain.Commit;

import java.util.List;
import java.util.stream.Collectors;

public class DistinctDevelopersNumber {

    public static void calculate(List<Commit> commits) {
        int distinctDevelopersNumber = commits.stream()
                .map(Commit::getDeveloper)
                .collect(Collectors.toSet())
                .size();
        System.out.println("Number of distinct committers: " + distinctDevelopersNumber);
    }

}
