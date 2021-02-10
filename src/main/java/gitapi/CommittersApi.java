package gitapi;

import domain.Committer;
import utils.ProcessExecutor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CommittersApi {

    public static List<Committer> getCommitters(String repoPath) {
        List<String> command = new ArrayList<>(Arrays.asList("git", "shortlog", "--summary", "--numbered", "--email", "--all"));
        List<String> processLogs = ProcessExecutor.getProcessLogs(repoPath, command);
        return mapProcessLogsToCommitters(processLogs);
    }

    private static List<Committer> mapProcessLogsToCommitters(List<String> processLogs) {
        return processLogs.stream()
                .map(CommittersApi::mapProcessLogToCommitter)
                .collect(Collectors.toList());
    }

    private static Committer mapProcessLogToCommitter(String processLog) {
        List<String> processLogElements = getProcessLogElements(processLog);
        int commitsNumber = getCommitsNumber(processLogElements);
        String mail = getMail(processLogElements);
        return Committer.builder()
                .withCommitsNumber(commitsNumber)
                .withMail(mail)
                .build();
    }

    private static List<String> getProcessLogElements(String processLog) {
        processLog = processLog.replaceFirst("\\s+", "");
        return Arrays.asList(processLog.split("\\s+"));
    }

    private static int getCommitsNumber(List<String> processLogElements) {
        return Integer.parseInt(processLogElements.get(0));
    }

    private static String getMail(List<String> processLogElements) {
        String mail = processLogElements.get(processLogElements.size() - 1);
        return mail.replaceAll("[<>]", "");
    }

}
