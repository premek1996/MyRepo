package processmetrics;

import domain.Commit;

import java.util.List;

/*
The sum of lines added minus lines deleted
from the source element.
 */

public class CodeChurn {

    public static void calculate(List<Commit> commits) {
        int addedLinesSum = commits.stream()
                .mapToInt(Commit::getAddedLines)
                .sum();
        int deleteLinesSum = commits.stream()
                .mapToInt(Commit::getDeletedLines)
                .sum();
        int codeChurn = addedLinesSum - deleteLinesSum;
        System.out.println("Code churn: " + codeChurn);
    }

}
