package processmetrics;

import domain.Commit;
import domain.InvestigatedSourceElement;

import java.util.List;

/*
The sum of lines added minus lines deleted
from the source element.
 */

public class CodeChurn implements ProcessMetric {

    private static final String METRIC_NAME = "CodeChurn";

    @Override
    public void compute(InvestigatedSourceElement investigatedSourceElement) {
        List<Commit> commits = investigatedSourceElement.getCommits();
        int addedLinesSum = commits.stream()
                .mapToInt(Commit::getAddedLines)
                .sum();
        int deleteLinesSum = commits.stream()
                .mapToInt(Commit::getDeletedLines)
                .sum();
        int codeChurn = addedLinesSum - deleteLinesSum;
        System.out.println("Code churn: " + codeChurn);
    }

    @Override
    public String getName() {
        return METRIC_NAME;
    }

}
