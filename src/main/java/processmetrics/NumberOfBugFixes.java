package processmetrics;

import domain.Commit;
import domain.InvestigatedSourceElement;
import domain.Metric;

import java.util.List;

/**
 * Name: Number of Bug Fixes
 * Description: The number of commits with 'bug' or 'fix' in the commit message.
 * Reference: Linus W. Dietz, Robin Lichtenthälery, Adam Tornhillz and Simon Harrer.
 * 2019. Code Process Metrics in University Programming Education. Software
 * Engineering
 */

public class NumberOfBugFixes implements ProcessMetric {

    private static final String METRIC_NAME = "NumberOfBugFixes";
    private static final List<String> WORDS = List.of("fix", "bug");

    @Override
    public Metric compute(InvestigatedSourceElement investigatedSourceElement) {
        long numberOfBugFixes = investigatedSourceElement.getCommits().stream()
                .map(Commit::getMessage)
                .map(String::toLowerCase)
                .filter(NumberOfBugFixes::containsWords)
                .count();
        return new Metric(METRIC_NAME, numberOfBugFixes);
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
