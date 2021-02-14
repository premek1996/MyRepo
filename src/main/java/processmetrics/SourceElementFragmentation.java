package processmetrics;

import domain.InvestigatedSourceElement;
import gitapi.SourceElementFragmentationApi;

/*
A metric describing how fragmented the work
on single source element is across developers.
 */

public class SourceElementFragmentation {

    public static void calculate(InvestigatedSourceElement investigatedSourceElement) {
        long sourceElementFragmentationAcrossDevelopers =
                SourceElementFragmentationApi.getSourceElementFragmentationAcrossDevelopers
                        (investigatedSourceElement);
        System.out.println("Source element fragmentation across developers: " +
                sourceElementFragmentationAcrossDevelopers);
    }

}
