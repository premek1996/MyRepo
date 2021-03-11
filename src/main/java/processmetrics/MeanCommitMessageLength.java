package processmetrics;

import domain.Commit;
import domain.InvestigatedSourceElement;
import domain.Metric;

/**
 * Name: Mean Commit Message Length
 * Description: The mean number of characters in commit messages.
 * Reference: Linus W. Dietz, Robin Lichtenthälery, Adam Tornhillz and Simon Harrer.
 * 2019. Code Process Metrics in University Programming Education. Software
 * Engineering
 */

public class MeanCommitMessageLength implements ProcessMetric {

    private static final String METRIC_NAME = "MeanCommitMessageLength";

    @Override
    public Metric compute(InvestigatedSourceElement investigatedSourceElement) {
        double meanCommitMessageLength = investigatedSourceElement.getCommits().stream()
                .map(Commit::getMessage)
                .mapToInt(String::length)
                .average()
                .orElse(0);
        return new Metric(METRIC_NAME, meanCommitMessageLength);
    }

    @Override
    public String getName() {
        return METRIC_NAME;
    }

}
