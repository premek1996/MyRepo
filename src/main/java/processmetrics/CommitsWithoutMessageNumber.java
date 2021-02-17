package processmetrics;

import domain.Commit;
import domain.InvestigatedSourceElement;

/*
The number of previous modifications
without any comment message.
 */

public class CommitsWithoutMessageNumber implements ProcessMetric {

    private static final String METRIC_NAME = "CommitsWithoutMessageNumber";

    @Override
    public void compute(InvestigatedSourceElement investigatedSourceElement) {
        long commitsWithoutMessageNumber = investigatedSourceElement.getCommits().stream()
                .map(Commit::getMessage)
                .filter(String::isEmpty)
                .count();
        System.out.println("Number of commits without message: " + commitsWithoutMessageNumber);
    }

    @Override
    public String getName() {
        return METRIC_NAME;
    }

}
