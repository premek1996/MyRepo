package processmetrics;

import domain.Commit;
import domain.Developer;
import domain.InvestigatedSourceElement;

/*
The metric represents the average number of commits per developer.
 */

public class DeveloperCommitsAverageNumber implements ProcessMetric {

    private static final String METRIC_NAME = "DeveloperCommitsAverageNumber";

    @Override
    public void compute(InvestigatedSourceElement investigatedSourceElement) {
        double developerCommitsAverageNumber = investigatedSourceElement.getCommits().stream()
                .map(Commit::getDeveloper)
                .distinct()
                .mapToInt(Developer::getCommits)
                .average()
                .orElse(0);
        System.out.println("Average number of commits per developer: " + developerCommitsAverageNumber);
    }

    @Override
    public String getName() {
        return METRIC_NAME;
    }

}
