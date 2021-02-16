package processmetrics;

import domain.Commit;

import java.util.List;

/*
The number of commits with 'refactor' or 'improve'
in the commit message.
 */

public class RefactoringsNumber {

    private static final List<String> WORDS = List.of("refactor", "improve");

    public static void calculate(List<Commit> commits) {
        long refactoringsNumber = commits.stream()
                .map(Commit::getMessage)
                .map(String::toLowerCase)
                .filter(RefactoringsNumber::containsWords)
                .count();
        System.out.println("The number of refactorings: " + refactoringsNumber);
    }

    private static boolean containsWords(String commitMessage) {
        return WORDS.stream()
                .anyMatch(commitMessage::contains);
    }

}
