package processmetrics;

import domain.InvestigatedSourceElement;
import domain.Metric;

public interface ProcessMetric {

    Metric compute(InvestigatedSourceElement investigatedSourceElement);

    String getName();

}
