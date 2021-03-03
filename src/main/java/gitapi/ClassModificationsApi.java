package gitapi;

import domain.InvestigatedSourceElement;
import domain.SourceElementModification;
import utils.ProcessExecutor;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ClassModificationsApi {

    private ClassModificationsApi() {
    }

    public static List<SourceElementModification> getSourceElementModifications(InvestigatedSourceElement investigatedSourceElement) {
        resetRepository(investigatedSourceElement);
        List<String> command = List.of("git", "log", "--numstat", "--oneline", "--follow", investigatedSourceElement.getFilePath());
        List<String> processLogs = ProcessExecutor.getProcessLogs(investigatedSourceElement.getRepositoryPath(), command);
        return getSourceElementModifications(processLogs);
    }

    private static void resetRepository(InvestigatedSourceElement investigatedSourceElement) {
        List<String> command = List.of("git", "reset", "--hard", investigatedSourceElement.getCurrentHashCommit());
        List<String> processLogs = ProcessExecutor.getProcessLogs(investigatedSourceElement.getRepositoryPath(), command);
    }

    private static List<SourceElementModification> getSourceElementModifications(List<String> processLogs) {
        int sourceElementModificationsNumber = processLogs.size() / 2;
        return Stream.iterate(0, index -> index < sourceElementModificationsNumber, index -> index + 1)
                .map(toSourceElementModification(processLogs))
                .collect(Collectors.toList());
    }

    private static Function<Integer, SourceElementModification> toSourceElementModification(List<String> processLogs) {
        return index -> {
            System.out.println(processLogs);
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
    }

    private static int getAddedLines(String processLog) {
        String[] processLogElements = processLog.split("\\s+");
        return Integer.parseInt(processLogElements[0]);
    }

    private static int getDeletedLines(String processLog) {
        String[] processLogElements = processLog.split("\\s+");
        return Integer.parseInt(processLogElements[1]);
    }

}
