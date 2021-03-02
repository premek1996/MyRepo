package processmetrics;

import domain.Commit;
import domain.InvestigatedSourceElement;
import domain.Metric;

/*
The metric represents the average number
of characters in commit messages.
 */

public class CommitMessageAverageLength implements ProcessMetric {

    private static final String METRIC_NAME = "CommitMessageAverageLength";

    @Override
    public Metric compute(InvestigatedSourceElement investigatedSourceElement) {
        double commitMessageAverageLength = investigatedSourceElement.getCommits().stream()
                .map(Commit::getMessage)
                .mapToInt(String::length)
                .average()
                .orElse(0);
        return new Metric(METRIC_NAME, commitMessageAverageLength);
    }

    @Override
    public String getName() {
        return METRIC_NAME;
    }

}
