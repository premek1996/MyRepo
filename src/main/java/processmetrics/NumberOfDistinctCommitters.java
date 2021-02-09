package processmetrics;

import domain.Commit;

import java.util.List;
import java.util.stream.Collectors;

public class NumberOfDistinctCommitters {

    public static void calculate(List<Commit> commits) {
        int numberOfDistinctCommitters = +commits.stream().map(Commit::getCommitter).collect(Collectors.toSet()).size();
        System.out.println("Number of distinct committers: " + numberOfDistinctCommitters);
    }
}
