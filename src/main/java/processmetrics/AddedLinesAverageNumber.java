package processmetrics;

import domain.Commit;

import java.util.List;

/*
The average number of lines of code added
to the source element.
 */

public class AddedLinesAverageNumber {

    public static void calculate(List<Commit> commits) {
        double addedLinesAverageNumber = commits.stream()
                .mapToInt(Commit::getAddedLines)
                .average()
                .orElse(0);
        System.out.println("Average number of added lines: " + addedLinesAverageNumber);
    }

}
