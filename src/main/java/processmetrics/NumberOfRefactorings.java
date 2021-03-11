package processmetrics;

import domain.Commit;
import domain.InvestigatedSourceElement;
import domain.Metric;

import java.util.List;

/**
 * Name: Number of Refactorings
 * Description: The number of commits with 'refactor' or 'improve' in the commit message.
 * Reference: Linus W. Dietz, Robin Lichtenthälery, Adam Tornhillz and Simon Harrer.
 * 2019. Code Process Metrics in University Programming Education. Software
 * Engineering
 */

public class NumberOfRefactorings implements ProcessMetric {

    private static final String METRIC_NAME = "NumberOfRefactorings";
    private static final List<String> WORDS = List.of("refactor", "improve");

    @Override
    public Metric compute(InvestigatedSourceElement investigatedSourceElement) {
        long numberOfRefactorings = investigatedSourceElement.getCommits().stream()
                .map(Commit::getMessage)
                .map(String::toLowerCase)
                .filter(NumberOfRefactorings::containsWords)
                .count();
        return new Metric(METRIC_NAME, numberOfRefactorings);
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
