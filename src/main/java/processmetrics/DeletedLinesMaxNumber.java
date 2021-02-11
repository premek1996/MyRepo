package processmetrics;

import domain.Commit;

import java.util.List;

/*
The maximum number of lines of code deleted
with one commit from the source element.
 */

public class DeletedLinesMaxNumber {

    public static void calculate(List<Commit> commits) {
        int deletedLinesMaxNumber = commits.stream()
                .mapToInt(Commit::getDeletedLinesNumber)
                .max()
                .orElse(0);
        System.out.println("Max number of deleted lines: " + deletedLinesMaxNumber);
    }

}
