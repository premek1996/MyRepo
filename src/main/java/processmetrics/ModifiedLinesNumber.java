package processmetrics;

import domain.Commit;

import java.util.List;

/*
The value of the metrics is equal to the sum of all lines of source code
which were added or removed in a given Java class/method.
 */

public class ModifiedLinesNumber {

    public static void calculate(List<Commit> commits) {
        int modifiedLinesNumber = commits.stream()
                .mapToInt(Commit::getModifiedLines)
                .sum();
        System.out.println("Number of modified lines: " + modifiedLinesNumber);
    }

}
