package processmetrics;

import domain.InvestigatedSourceElement;
import domain.Metric;
import gitapi.SourceElementFragmentationApi;

/**
 * Name: Author Fragmentation
 * Description: A metric describing how fragmented the work on single source element is across authors.
 * Reference: Linus W. Dietz, Robin Lichtenthälery, Adam Tornhillz and Simon Harrer.
 * 2019. Code Process Metrics in University Programming Education. Software
 * Engineering
 */

public class AuthorFragmentation implements ProcessMetric {

    private static final String METRIC_NAME = "AuthorFragmentation";

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
