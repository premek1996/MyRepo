package processmetrics;

import domain.Commit;
import domain.Developer;
import domain.InvestigatedSourceElement;
import domain.Metric;

import java.util.Objects;

/*
The metric represents the average number of commits per developer.
 */

public class DeveloperCommitsAverageNumber implements ProcessMetric {

    private static final String METRIC_NAME = "DeveloperCommitsAverageNumber";

    @Override
    public Metric compute(InvestigatedSourceElement investigatedSourceElement) {
        double developerCommitsAverageNumber = investigatedSourceElement.getCommits().stream()
                .map(Commit::getDeveloper)
                .distinct()
                .filter(Objects::nonNull)
                .mapToInt(Developer::getCommits)
                .average()
                .orElse(0);
        return new Metric(METRIC_NAME, developerCommitsAverageNumber);
    }

    @Override
    public String getName() {
        return METRIC_NAME;
    }

}
