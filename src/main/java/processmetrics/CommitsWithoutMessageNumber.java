package processmetrics;

import domain.Commit;
import domain.InvestigatedSourceElement;
import domain.Metric;

/*
The number of previous modifications
without any comment message.
 */

public class CommitsWithoutMessageNumber implements ProcessMetric {

    private static final String METRIC_NAME = "CommitsWithoutMessageNumber";

    @Override
    public Metric compute(InvestigatedSourceElement investigatedSourceElement) {
        long commitsWithoutMessageNumber = investigatedSourceElement.getCommits().stream()
                .map(Commit::getMessage)
                .filter(String::isEmpty)
                .count();
        System.out.println("Number of commits without message: " + commitsWithoutMessageNumber);
        return new Metric(METRIC_NAME, commitsWithoutMessageNumber);
    }

    @Override
    public String getName() {
        return METRIC_NAME;
    }

}
