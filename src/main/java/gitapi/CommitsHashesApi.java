package gitapi;

import utils.ProcessExecutor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CommitsHashesApi {

    public static List<String> getCommitsHashesWhichChangedFile(String repoPath, String filePath) {
        List<String> command = new ArrayList<>
                (Arrays.asList("git", "log", "--follow", "--oneline", "--", filePath));
        List<String> processLogs = ProcessExecutor.getProcessLogs(repoPath, command);
        return mapProcessLogsToCommitsHashes(processLogs);
    }

    private static List<String> mapProcessLogsToCommitsHashes(List<String> processLogs) {
        return processLogs.stream()
                .map(CommitsHashesApi::mapProcessLogToCommitHash)
                .collect(Collectors.toList());
    }

    private static String mapProcessLogToCommitHash(String processLog) {
        return processLog.split(" ")[0];
    }

}
