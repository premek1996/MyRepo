package processmetrics;

import domain.Commit;
import domain.InvestigatedSourceElement;
import domain.Metric;

import java.util.List;

/*
The number of commits with 'refactor' or 'improve'
in the commit message.
 */

public class RefactoringsNumber implements ProcessMetric<Long> {

    private static final String METRIC_NAME = "RefactoringsNumber";
    private static final List<String> WORDS = List.of("refactor", "improve");

    @Override
    public Metric<Long> compute(InvestigatedSourceElement investigatedSourceElement) {
        long refactoringsNumber = investigatedSourceElement.getCommits().stream()
                .map(Commit::getMessage)
                .map(String::toLowerCase)
                .filter(RefactoringsNumber::containsWords)
                .count();
        System.out.println("The number of refactorings: " + refactoringsNumber);
        return new Metric<>(METRIC_NAME, refactoringsNumber);
    }

    private static boolean containsWords(String commitMessage) {
        return WORDS.stream()
                .anyMatch(commitMessage::contains);
    }

    @Override
    public String getName() {
        return METRIC_NAME;
    }

}
