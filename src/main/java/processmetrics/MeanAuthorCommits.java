package processmetrics;

import domain.Commit;
import domain.Developer;
import domain.InvestigatedSourceElement;
import domain.Metric;

/**
 * Name: Mean Author Commits
 * Description: The mean number of commits per author.
 * Reference: Linus W. Dietz, Robin Lichtenthälery, Adam Tornhillz and Simon Harrer.
 * 2019. Code Process Metrics in University Programming Education. Software
 * Engineering
 */

public class MeanAuthorCommits implements ProcessMetric {

    private static final String METRIC_NAME = "MeanAuthorCommits";

    @Override
    public Metric compute(InvestigatedSourceElement investigatedSourceElement) {
        double meanAuthorCommits = investigatedSourceElement.getCommits().stream()
                .map(Commit::getDeveloper)
                .distinct()
                .mapToInt(Developer::getCommits)
                .average()
                .orElse(0);
        return new Metric(METRIC_NAME, meanAuthorCommits);
    }

    @Override
    public String getName() {
        return METRIC_NAME;
    }

}
