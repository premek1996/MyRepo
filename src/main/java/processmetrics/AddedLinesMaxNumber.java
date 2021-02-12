package processmetrics;

import domain.Commit;

import java.util.List;

/*
The maximum number of lines of code added
with one commit to the source element.
 */

public class AddedLinesMaxNumber {

    public static void calculate(List<Commit> commits) {
        int addedLinesMaxNumber = commits.stream()
                .mapToInt(Commit::getAddedLines)
                .max()
                .orElse(0);
        System.out.println("Max number of added lines: " + addedLinesMaxNumber);
    }

}
