package processmetrics;

import domain.Commit;

import java.util.Arrays;
import java.util.List;

/*
The number of commits with 'bug' or 'fix'
in the commit message.
 */

public class BugFixesNumber {

    public static void calculate(List<Commit> commits) {
        List<String> words = Arrays.asList("fix", "bug");
        long bugFixesNumber = commits.stream()
                .map(Commit::getMessage)
                .map(String::toLowerCase)
                .filter(commitMessage -> containsWords(commitMessage, words))
                .count();
        System.out.println("The number of bug fixes: " + bugFixesNumber);
    }

    private static boolean containsWords(String commitMessage, List<String> words) {
        return words.stream()
                .anyMatch(commitMessage::contains);
    }

}
