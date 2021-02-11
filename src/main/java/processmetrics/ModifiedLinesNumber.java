package processmetrics;

import domain.Commit;

import java.util.List;

public class ModifiedLinesNumber {

    public static void calculate(List<Commit> commits) {
        int modifiedLinesNumber = commits.stream()
                .mapToInt(Commit::getModifiedLinesNumber)
                .sum();
        System.out.println("Number of modified lines: " + modifiedLinesNumber);
    }

}
