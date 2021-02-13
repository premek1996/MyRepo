package gitapi;

import domain.SourceElementModification;
import utils.ProcessExecutor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ClassModificationsApi {

    public static List<SourceElementModification> getSourceElementModifications
            (String repoPath, String filePath) {
        List<String> command = new ArrayList<>
                (Arrays.asList("git", "log", "--numstat", "--oneline", "--follow", filePath));
        List<String> processLogs = ProcessExecutor.getProcessLogs(repoPath, command);
        return getSourceElementModifications(processLogs);
    }

    private static List<SourceElementModification> getSourceElementModifications(List<String> processLogs) {
        List<SourceElementModification> sourceElementModifications = new ArrayList<>();
        int sourceElementModificationsNumber = processLogs.size() / 2;
        for (int index = 0; index < sourceElementModificationsNumber; index++) {
            sourceElementModifications.add(getSourceElementModification(index, processLogs));
        }
        return sourceElementModifications;
    }

    private static SourceElementModification getSourceElementModification(int index, List<String> processLogs) {
        String hash = getHash(processLogs.get(index * 2));
        int addedLines = getAddedLines(processLogs.get(index * 2 + 1));
        int deletedLines = getDeletedLines(processLogs.get(index * 2 + 1));
        return SourceElementModification.builder()
                .withHash(hash)
                .withAddedLines(addedLines)
                .withDeletedLines(deletedLines)
                .build();
    }

    private static String getHash(String processLogs) {
        String[] processLogElements = processLogs.split("\\s+");
        return processLogElements[0];
    }

    private static int getAddedLines(String processLogs) {
        String[] processLogElements = processLogs.split("\\s+");
        return Integer.parseInt(processLogElements[0]);
    }

    private static int getDeletedLines(String processLogs) {
        String[] processLogElements = processLogs.split("\\s+");
        return Integer.parseInt(processLogElements[1]);
    }

}
