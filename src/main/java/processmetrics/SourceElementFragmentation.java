package processmetrics;

import domain.InvestigatedSourceElement;
import gitapi.SourceElementFragmentationApi;

/*
A metric describing how fragmented the work
on single source element is across developers.
 */

public class SourceElementFragmentation implements ProcessMetric {

    private static final String METRIC_NAME = "SourceElementFragmentation";

    @Override
    public void compute(InvestigatedSourceElement investigatedSourceElement) {
        long sourceElementFragmentationAcrossDevelopers =
                SourceElementFragmentationApi.getSourceElementFragmentationAcrossDevelopers
                        (investigatedSourceElement);
        System.out.println("Source element fragmentation across developers: " +
                sourceElementFragmentationAcrossDevelopers);
    }

    @Override
    public String getName() {
        return METRIC_NAME;
    }

}
