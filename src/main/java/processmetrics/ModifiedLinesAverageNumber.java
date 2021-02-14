package processmetrics;

import domain.Commit;

import java.util.List;

/*
The average number of modified lines (which were
added or removed) in a given Java class/method.
 */

public class ModifiedLinesAverageNumber {

    public static void calculate(List<Commit> commits) {
        double modifiedLinesAverageNumber = commits.stream()
                .mapToInt(Commit::getModifiedLines)
                .average()
                .orElse(0);
        System.out.println("Average number of modified lines: " + modifiedLinesAverageNumber);
    }

}
