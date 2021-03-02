package processmetrics;

import domain.InvestigatedSourceElement;
import domain.Metric;

import java.util.List;
import java.util.stream.Collectors;

public class ProcessMetricsCalculator {

    private static final List<ProcessMetric> PROCESS_METRICS = List.of(
            new AddedLinesAverageNumber(),
            new AddedLinesMaxNumber(),
            new Age(),
            new AverageTimeBetweenCommits(),
            new BugFixesNumber(),
            new CodeChurn(),
            new CommitMessageAverageLength(),
            new CommitsNumber(),
            new CommitsWithoutMessageNumber(),
            new DaysWithCommits(),
            new DeletedLinesAverageNumber(),
            new DeletedLinesMaxNumber(),
            new DeveloperCommitsAverageNumber(),
            new DistinctDevelopersNumber(),
            new ModifiedLinesAverageNumber(),
            new ModifiedLinesMaxNumber(),
            new RefactoringsNumber(),
            new SourceElementFragmentation(),
            new TimePassedSinceLastCommit()
    );

    public static List<Metric> getMetrics(InvestigatedSourceElement investigatedSourceElement) {
        return PROCESS_METRICS.stream()
                .map(processMetric -> processMetric.compute(investigatedSourceElement))
                .collect(Collectors.toList());
    }

}
