package processmetrics;

import domain.Commit;
import domain.InvestigatedSourceElement;
import domain.Metric;

import java.util.List;

/*
The number of commits with 'bug' or 'fix'
in the commit message.
 */

public class BugFixesNumber implements ProcessMetric<Long> {

    private static final String METRIC_NAME = "BugFixesNumber";
    private static final List<String> WORDS = List.of("fix", "bug");

    @Override
    public Metric<Long> compute(InvestigatedSourceElement investigatedSourceElement) {
        long bugFixesNumber = investigatedSourceElement.getCommits().stream()
                .map(Commit::getMessage)
                .map(String::toLowerCase)
                .filter(BugFixesNumber::containsWords)
                .count();
        System.out.println("The number of bug fixes: " + bugFixesNumber);
        return new Metric<>(METRIC_NAME, bugFixesNumber);
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
