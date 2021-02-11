package processmetrics;

import domain.Commit;

import java.util.List;

/*
The metric represents the average number
of characters in commit messages.
 */

public class CommitMessageAverageLength {

    public static void calculate(List<Commit> commits) {
        double commitMessageAverageLength = commits.stream()
                .map(Commit::getMessage)
                .mapToInt(String::length)
                .average()
                .orElse(0);
        System.out.println("the average number of characters in commit messages: " + commitMessageAverageLength);
    }

}
