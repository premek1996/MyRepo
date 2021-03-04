package gitapi;

import domain.Commit;
import domain.Developer;
import domain.InvestigatedSourceElement;
import utils.ProcessExecutor;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ClassModificationsApi {

    private ClassModificationsApi() {
    }

    public static List<Commit> getCommits(InvestigatedSourceElement investigatedSourceElement) {
        resetRepository(investigatedSourceElement);
        List<String> command = List.of("git", "log", "--oneline", "--follow", "--numstat",
                "--format=%H%n%ae%n%cd%n%s", "--date=format:%Y-%m-%d", investigatedSourceElement.getFilePath());
        List<String> processLogs = ProcessExecutor.getProcessLogs(investigatedSourceElement.getRepositoryPath(), command);
        processLogs.removeAll(Collections.singletonList(""));
        return getCommits(processLogs, investigatedSourceElement);
    }

    private static void resetRepository(InvestigatedSourceElement investigatedSourceElement) {
        List<String> command = List.of("git", "reset", "--hard", investigatedSourceElement.getCurrentHashCommit());
        List<String> processLogs = ProcessExecutor.getProcessLogs(investigatedSourceElement.getRepositoryPath(), command);
    }

    private static List<Commit> getCommits(List<String> processLogs, InvestigatedSourceElement investigatedSourceElement) {
        int commitsNumber = processLogs.size() / 5;
        return Stream.iterate(0, index -> index < commitsNumber, index -> index + 1)
                .map(toCommit(processLogs, investigatedSourceElement))
                .collect(Collectors.toList());
    }

    private static Function<Integer, Commit> toCommit(List<String> processLogs, InvestigatedSourceElement investigatedSourceElement) {
        return index -> {
            String hash = processLogs.get(index * 5);
            String mail = processLogs.get(index * 5 + 1);
            Developer developer = investigatedSourceElement.getDeveloper(mail);
            LocalDate date = LocalDate.parse(processLogs.get(index * 5 + 2));
            String message = processLogs.get(index * 5 + 3);
            int addedLines = getAddedLines(processLogs.get(index * 5 + 4));
            int deletedLines = getDeletedLines(processLogs.get(index * 5 + 4));
            return Commit.builder()
                    .withHash(hash)
                    .withDeveloper(developer)
                    .withDate(date)
                    .withMessage(message)
                    .withAddedLines(addedLines)
                    .withDeletedLines(deletedLines)
                    .build();
        };
    }

    private static int getAddedLines(String processLog) {
        String[] processLogElements = processLog.split("\\s+");
        return Integer.parseInt(processLogElements[0]);
    }

    private static int getDeletedLines(String processLog) {
        String[] processLogElements = processLog.split("\\s+");
        return Integer.parseInt(processLogElements[1]);
    }

    /*public static List<SourceElementModification> getSourceElementModifications(InvestigatedSourceElement investigatedSourceElement) {
        resetRepository(investigatedSourceElement);
        List<String> command = List.of("git", "log", "--numstat", "--oneline", "--follow", investigatedSourceElement.getFilePath());
        List<String> processLogs = ProcessExecutor.getProcessLogs(investigatedSourceElement.getRepositoryPath(), command);
        return getSourceElementModifications(processLogs);
    }


    private static List<SourceElementModification> getSourceElementModifications(List<String> processLogs) {
        int sourceElementModificationsNumber = processLogs.size() / 2;
        return Stream.iterate(0, index -> index < sourceElementModificationsNumber, index -> index + 1)
                .map(toSourceElementModification(processLogs))
                .collect(Collectors.toList());
    }

    private static Function<Integer, SourceElementModification> toSourceElementModification(List<String> processLogs) {
        return index -> {
            //System.out.println(processLogs);
            String hash = getHash(processLogs.get(index * 2));
            int addedLines = getAddedLines(processLogs.get(index * 2 + 1));
            int deletedLines = getDeletedLines(processLogs.get(index * 2 + 1));
            return SourceElementModification.builder()
                    .withHash(hash)
                    .withAddedLines(addedLines)
                    .withDeletedLines(deletedLines)
                    .build();
        };
    }

    private static String getHash(String processLog) {
        String[] processLogElements = processLog.split("\\s+");
        return processLogElements[0];
    }*/

}
