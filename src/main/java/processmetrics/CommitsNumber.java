package processmetrics;

import domain.InvestigatedSourceElement;


/*
The metric represents the number of commits of a given
Java class/method during development of the investigated
release of a software system.
 */

public class CommitsNumber implements ProcessMetric {

    private static final String METRIC_NAME = "CommitsNumber";

    @Override
    public void compute(InvestigatedSourceElement investigatedSourceElement) {
        int commitsNumber = investigatedSourceElement.getCommits().size();
        System.out.println("Number of commits: " + commitsNumber);
    }

    @Override
    public String getName() {
        return METRIC_NAME;
    }

}
