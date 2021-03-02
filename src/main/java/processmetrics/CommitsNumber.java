package processmetrics;

import domain.InvestigatedSourceElement;
import domain.Metric;


/*
The metric represents the number of commits of a given
Java class/method during development of the investigated
release of a software system.
 */

public class CommitsNumber implements ProcessMetric {

    private static final String METRIC_NAME = "CommitsNumber";

    @Override
    public Metric compute(InvestigatedSourceElement investigatedSourceElement) {
        int commitsNumber = investigatedSourceElement.getCommits().size();
        System.out.println("Number of commits: " + commitsNumber);
        return new Metric(METRIC_NAME, commitsNumber);
    }

    @Override
    public String getName() {
        return METRIC_NAME;
    }

}
