package processmetrics;

import domain.Commit;
import domain.Developer;
import domain.InvestigatedSourceElement;
import domain.Metric;

/*
The metric represents the average number of commits per developer.
 */

public class DeveloperCommitsAverageNumber implements ProcessMetric<Double> {

    private static final String METRIC_NAME = "DeveloperCommitsAverageNumber";

    @Override
    public Metric<Double> compute(InvestigatedSourceElement investigatedSourceElement) {
        double developerCommitsAverageNumber = investigatedSourceElement.getCommits().stream()
                .map(Commit::getDeveloper)
                .distinct()
                .mapToInt(Developer::getCommits)
                .average()
                .orElse(0);
        System.out.println("Average number of commits per developer: " + developerCommitsAverageNumber);
        return new Metric<>(METRIC_NAME, developerCommitsAverageNumber);
    }

    @Override
    public String getName() {
        return METRIC_NAME;
    }

}
