package processmetrics;

import domain.Commit;
import domain.InvestigatedSourceElement;
import domain.Metric;

/**
 * Name: Number of Commits Without Message
 * Description: The number of previous modifications without any comment message.
 * Reference: Péter Gyimesi. 2017. Automatic calculation of process metrics and their bug
 * prediction capabilities. Acta Cybernetica, 23(2), 537-559
 */

public class NumberOfCommitsWithoutMessage implements ProcessMetric {

    private static final String METRIC_NAME = "NumberOfCommitsWithoutMessage";

    @Override
    public Metric compute(InvestigatedSourceElement investigatedSourceElement) {
        long numberOfCommitsWithoutMessage = investigatedSourceElement.getCommits().stream()
                .map(Commit::getMessage)
                .filter(String::isEmpty)
                .count();
        return new Metric(METRIC_NAME, numberOfCommitsWithoutMessage);
    }

    @Override
    public String getName() {
        return METRIC_NAME;
    }

}
