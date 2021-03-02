package processmetrics;

import domain.InvestigatedSourceElement;
import domain.Metric;

public interface ProcessMetric<T extends Number> {

    Metric<T> compute(InvestigatedSourceElement investigatedSourceElement);

    String getName();

}
