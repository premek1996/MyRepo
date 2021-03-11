package processmetrics;

import domain.Commit;
import domain.InvestigatedSourceElement;
import domain.Metric;

import java.util.List;

/**
 * Name: Average Time Between Changes
 * Description: The sum of lines added minus lines deleted
 * from the source element.
 * Reference: Péter Gyimesi. 2017. Automatic calculation of process metrics and their bug
 * prediction capabilities. Acta Cybernetica, 23(2), 537-559
 */

public class CodeChurn implements ProcessMetric {

    private static final String METRIC_NAME = "CodeChurn";

    @Override
    public Metric compute(InvestigatedSourceElement investigatedSourceElement) {
        List<Commit> commits = investigatedSourceElement.getCommits();
        int addedLinesSum = commits.stream()
                .mapToInt(Commit::getAddedLines)
                .sum();
        int deleteLinesSum = commits.stream()
                .mapToInt(Commit::getDeletedLines)
                .sum();
        int codeChurn = addedLinesSum - deleteLinesSum;
        return new Metric(METRIC_NAME, codeChurn);
    }

    @Override
    public String getName() {
        return METRIC_NAME;
    }

}
