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
import processmetrics.RefactoringsNumber;
import processmetrics.SourceElementFragmentation;
import processmetrics.TimePassedSinceLastCommit;

import java.io.IOException;


public class Main {

    private static final String repoPath = "C:\\Users\\przem\\java-metrics-source-repos\\apache\\syncope";
    private static final String filePath = "client/idrepo/ui/src/main/java/org/apache/syncope/client/ui/commons/ConnIdSpecialName.java";
    private static final String currentHashCommit = "114c412afbfba24ffb4fbc804e5308a823a16a78";

    /*private static final String repoPath = "C:\\Users\\przem\\IdeaProjects\\javaparser";
    private static final String filePath = "src/main/java/Main.java";
    private static final String currentHashCommit = "9569b49";*/

    public static void main(String[] args) throws IOException, InterruptedException {

        InvestigatedSourceElement investigatedSourceElement = InvestigatedSourceElement.builder()
                .withRepoPath(repoPath)
                .withFilePath(filePath)
                .withStartLine(1)
                .withEndLine(38)
                .withCurrentHashCommit(currentHashCommit)
                .build();

        ClassChangeHistoryTracker classHistoryTracker =
                new ClassChangeHistoryTracker(investigatedSourceElement);

        AddedLinesAverageNumber.calculate(classHistoryTracker.getCommits());
        AddedLinesMaxNumber.calculate(classHistoryTracker.getCommits());
        Age.calculate(classHistoryTracker.getCommits(), investigatedSourceElement.getCurrentDate());
        AverageTimeBetweenCommits.calculate(classHistoryTracker.getCommits());
        BugFixesNumber.calculate(classHistoryTracker.getCommits());
        CodeChurn.calculate(classHistoryTracker.getCommits());
        CommitMessageAverageLength.calculate(classHistoryTracker.getCommits());
        CommitsNumber.calculate(classHistoryTracker.getCommits());
        CommitsWithoutMessageNumber.calculate(classHistoryTracker.getCommits());
        DaysWithCommits.calculate(classHistoryTracker.getCommits());
        DeletedLinesAverageNumber.calculate(classHistoryTracker.getCommits());
        DeletedLinesMaxNumber.calculate(classHistoryTracker.getCommits());
        DeveloperCommitsAverageNumber.calculate(classHistoryTracker.getCommits());
        DistinctDevelopersNumber.calculate(classHistoryTracker.getCommits());
        ModifiedLinesAverageNumber.calculate(classHistoryTracker.getCommits());
        ModifiedLinesMaxNumber.calculate(classHistoryTracker.getCommits());
        RefactoringsNumber.calculate(classHistoryTracker.getCommits());
        SourceElementFragmentation.calculate(investigatedSourceElement);
        TimePassedSinceLastCommit.calculate(classHistoryTracker.getCommits(), investigatedSourceElement.getCurrentDate());
    }

}
