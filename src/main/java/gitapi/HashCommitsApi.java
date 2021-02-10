package gitapi;

import utils.ProcessExecutor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class HashCommitsApi {

    public static List<String> getHashCommits(String repoPath, String filePath) {
        List<String> command = new ArrayList<>(Arrays.asList("git", "log", "--follow", "--oneline", "--", filePath));
        List<String> processLogs = ProcessExecutor.getProcessLogs(repoPath, command);
        return mapProcessLogsToHashCommits(processLogs);
    }

    private static List<String> mapProcessLogsToHashCommits(List<String> processLogs) {
        return processLogs.stream()
                .map(HashCommitsApi::mapProcessLogToHashCommit)
                .collect(Collectors.toList());
    }

    private static String mapProcessLogToHashCommit(String processLog) {
        return processLog.split(" ")[0];
    }

}
