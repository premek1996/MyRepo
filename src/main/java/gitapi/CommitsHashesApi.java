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
        return getCommitsHashes(processLogs);
    }

    private static List<String> getCommitsHashes(List<String> processLogs) {
        return processLogs.stream()
                .map(CommitsHashesApi::getCommitHash)
                .collect(Collectors.toList());
    }

    private static String getCommitHash(String processLog) {
        return processLog.split(" ")[0];
    }

}
