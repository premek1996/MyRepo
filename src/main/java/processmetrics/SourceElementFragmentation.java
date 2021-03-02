package processmetrics;

import domain.InvestigatedSourceElement;
import domain.Metric;
import gitapi.SourceElementFragmentationApi;

/*
A metric describing how fragmented the work
on single source element is across developers.
 */

public class SourceElementFragmentation implements ProcessMetric {

    private static final String METRIC_NAME = "SourceElementFragmentation";

    @Override
    public Metric compute(InvestigatedSourceElement investigatedSourceElement) {
        long sourceElementFragmentationAcrossDevelopers =
                SourceElementFragmentationApi.getSourceElementFragmentationAcrossDevelopers
                        (investigatedSourceElement);
        return new Metric(METRIC_NAME, sourceElementFragmentationAcrossDevelopers);
    }

    @Override
    public String getName() {
        return METRIC_NAME;
    }

}
