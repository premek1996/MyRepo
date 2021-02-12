package processmetrics;

import domain.Commit;

import java.util.List;

/*
The average number of lines of code deleted
from the source element.
 */

public class DeletedLinesAverageNumber {

    public static void calculate(List<Commit> commits) {
        double deletedLinesAverageNumber = commits.stream()
                .mapToInt(Commit::getDeletedLines)
                .average()
                .orElse(0);
        System.out.println("Average number of deleted lines: " + deletedLinesAverageNumber);
    }

}
