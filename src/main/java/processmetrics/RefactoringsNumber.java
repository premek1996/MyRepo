package processmetrics;

import domain.Commit;

import java.util.Arrays;
import java.util.List;

/*
The number of commits with 'refactor' or 'improve'
in the commit message.
 */

public class RefactoringsNumber {

    public static void calculate(List<Commit> commits) {
        List<String> words = Arrays.asList("refactor", "improve");
        long refactoringsNumber = commits.stream()
                .map(Commit::getMessage)
                .map(String::toLowerCase)
                .filter(commitMessage -> containsWords(commitMessage, words))
                .count();
        System.out.println("The number of refactorings: " + refactoringsNumber);
    }

    private static boolean containsWords(String commitMessage, List<String> words) {
        return words.stream()
                .anyMatch(commitMessage::contains);
    }

}
