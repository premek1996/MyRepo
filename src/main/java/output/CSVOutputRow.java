package output;

import domain.InvestigatedSourceElement;
import domain.Metric;

import java.util.List;

public class CSVOutputRow {

    private final InvestigatedSourceElement investigatedSourceElement;
    private final List<Metric> metrics;

    public CSVOutputRow(InvestigatedSourceElement investigatedSourceElement, List<Metric> metrics) {
        this.investigatedSourceElement = investigatedSourceElement;
        this.metrics = metrics;
    }

    public InvestigatedSourceElement getInvestigatedSourceElement() {
        return investigatedSourceElement;
    }

    public List<Metric> getMetrics() {
        return metrics;
    }

}
