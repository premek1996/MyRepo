package gitapi;

import domain.SourceElementModification;
import utils.ProcessExecutor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//git log --follow --oneline --numstat --format=%H client/idrepo/ui/src/main/java/org/apache/syncope/client/ui/commons/ConnIdSpecialName.java

public class ClassModificationsApi {

    public static List<SourceElementModification> getSourceElementModifications
            (String repoPath, String filePath) {
        List<String> command = new ArrayList<>
                (Arrays.asList("git", "log", "--follow", "--oneline", "--numstat", "--format=%H", filePath));
        List<String> processLogs = ProcessExecutor.getProcessLogs(repoPath, command);
        processLogs.removeIf(""::equals);
        return new ArrayList<>();
    }


    private static List<String> getProcessLogElements(String processLog) {
        return Arrays.asList(processLog.split("\\s+"));
    }

    private static int getAddedLines(List<String> processLogs) {
        return Integer.parseInt(processLogs.get(0));
    }

    private static int getDeletedLines(List<String> processLogs) {
        return Integer.parseInt(processLogs.get(1));
    }

}
