package gitapi;

import domain.SourceElementModification;
import utils.ProcessExecutor;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ClassModificationsApi {

    private ClassModificationsApi() {
    }

    public static List<SourceElementModification> getSourceElementModifications(String repositoryPath,
                                                                                String filePath) {
        List<String> command = List.of("git", "log", "--numstat", "--oneline", "--follow", filePath);
        List<String> processLogs = ProcessExecutor.getProcessLogs(repositoryPath, command);
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
