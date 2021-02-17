package processmetrics;

import domain.InvestigatedSourceElement;

public interface ProcessMetric {

    void compute(InvestigatedSourceElement investigatedSourceElement);

    String getName();

}
