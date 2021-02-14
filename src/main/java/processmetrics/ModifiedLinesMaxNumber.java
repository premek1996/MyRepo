package processmetrics;

import domain.Commit;

import java.util.List;

/*
The maximum number of modified lines (which were
added or removed) in a given Java class/method.
 */

public class ModifiedLinesMaxNumber {

    public static void calculate(List<Commit> commits) {
        double modifiedLinesMaxNumber = commits.stream()
                .mapToInt(Commit::getModifiedLines)
                .max()
                .orElse(0);
        System.out.println("Max number of modified lines: " + modifiedLinesMaxNumber);
    }

}
