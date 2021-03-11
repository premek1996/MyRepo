package processmetrics;

import domain.InvestigatedSourceElement;
import domain.Metric;

import java.util.List;
import java.util.stream.Collectors;

public class ProcessMetricsCalculator {

    private static final List<ProcessMetric> PROCESS_METRICS = List.of(
            new AverageNumberOfAddedLines(),
            new MaxNumberOfAddedLines(),
            new Age(),
            new AverageTimeBetweenChanges(),
            new NumberOfBugFixes(),
            new CodeChurn(),
            new MeanCommitMessageLength(),
            new NumberOfRevisions(),
            new NumberOfCommitsWithoutMessage(),
            new DaysWithCommits(),
            new AverageNumberOfDeletedLines(),
            new MaxNumberOfDeletedLines(),
            new MeanAuthorCommits(),
            new NumberOfDistinctCommitters(),
            new AverageNumberOfModifiedLines(),
            new MaxNumberOfModifiedLines(),
            new NumberOfRefactorings(),
            new AuthorFragmentation(),
            new TimePassedSinceTheLastChange()
    );

    private ProcessMetricsCalculator() {
    }

    public static List<Metric> getMetrics(InvestigatedSourceElement investigatedSourceElement) {
        return PROCESS_METRICS.stream()
                .map(processMetric -> processMetric.compute(investigatedSourceElement))
                .collect(Collectors.toList());
    }

}
