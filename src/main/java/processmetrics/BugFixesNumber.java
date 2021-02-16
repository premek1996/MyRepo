package processmetrics;

import domain.Commit;

import java.util.List;

/*
The number of commits with 'bug' or 'fix'
in the commit message.
 */

public class BugFixesNumber {

    private static final List<String> WORDS = List.of("fix", "bug");

    public static void calculate(List<Commit> commits) {
        long bugFixesNumber = commits.stream()
                .map(Commit::getMessage)
                .map(String::toLowerCase)
                .filter(BugFixesNumber::containsWords)
                .count();
        System.out.println("The number of bug fixes: " + bugFixesNumber);
    }

    private static boolean containsWords(String commitMessage) {
        return WORDS.stream()
                .anyMatch(commitMessage::contains);
    }

}
