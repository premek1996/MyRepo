import changehistorytrackers.ClassChangeHistoryTracker;
import domain.InvestigatedSourceElement;
import processmetrics.AddedLinesAverageNumber;
import processmetrics.AddedLinesMaxNumber;
import processmetrics.Age;
import processmetrics.AverageTimeBetweenCommits;
import processmetrics.BugFixesNumber;
import processmetrics.CodeChurn;
import processmetrics.CommitMessageAverageLength;
import processmetrics.CommitsNumber;
import processmetrics.CommitsWithoutMessageNumber;
import processmetrics.DaysWithCommits;
import processmetrics.DeletedLinesAverageNumber;
import processmetrics.DeletedLinesMaxNumber;
import processmetrics.DeveloperCommitsAverageNumber;
import processmetrics.DistinctDevelopersNumber;
import processmetrics.ModifiedLinesAverageNumber;
import processmetrics.ModifiedLinesMaxNumber;
import processmetrics.ProcessMetric;
import processmetrics.RefactoringsNumber;
import processmetrics.SourceElementFragmentation;
import processmetrics.TimePassedSinceLastCommit;

import java.io.IOException;
import java.util.List;


public class Main {

    private static final String repositoryPath = "C:\\Users\\przem\\java-metrics-source-repos\\apache\\syncope";
    private static final String filePath = "client/idrepo/ui/src/main/java/org/apache/syncope/client/ui/commons/ConnIdSpecialName.java";
    private static final String currentHashCommit = "114c412afbfba24ffb4fbc804e5308a823a16a78";

    /*private static final String repositoryPath = "C:\\Users\\przem\\IdeaProjects\\javaparser";
    private static final String filePath = "src/main/java/Main.java";
    private static final String currentHashCommit = "9569b49";*/

    public static void main(String[] args) throws IOException, InterruptedException {

        final InvestigatedSourceElement investigatedSourceElement = InvestigatedSourceElement.builder()
                .withRepositoryPath(repositoryPath)
                .withFilePath(filePath)
                .withStartLine(1)
                .withEndLine(38)
                .withCurrentHashCommit(currentHashCommit)
                .build();

        List<ProcessMetric> processMetrics = List.of(
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

        InvestigatedSourceElement investigatedSourceElementWithSetCommits = new ClassChangeHistoryTracker(investigatedSourceElement).getInvestigatedSourceElementWithSetCommits();
        processMetrics.forEach(processMetric -> processMetric.compute(investigatedSourceElementWithSetCommits));

    }

}
