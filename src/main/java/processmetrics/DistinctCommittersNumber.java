package processmetrics;

import domain.Commit;

import java.util.List;
import java.util.stream.Collectors;

public class DistinctCommittersNumber {

    public static void calculate(List<Commit> commits) {
        int distinctCommittersNumber = commits.stream().map(Commit::getCommitter).collect(Collectors.toSet()).size();
        System.out.println("Number of distinct committers: " + distinctCommittersNumber);
    }

}
