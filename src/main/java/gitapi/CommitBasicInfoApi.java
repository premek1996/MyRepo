package gitapi;

import domain.CommitBasicInfo;
import utils.ProcessExecutor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CommitBasicInfoApi {

    private static final String GIT_ERROR = "fatal";
    public static final List<String> errors = new ArrayList<>();

    private CommitBasicInfoApi() {
    }

    public static boolean isCommitAvailable(String repositoryPath,
                                            String hash) {
        List<String> command = List.of("git", "show", "-s", "--format=%ae%n%cd%n%B",
                "--date=format:%Y-%m-%d", hash);
        List<String> processLogs = ProcessExecutor.getProcessLogs(repositoryPath, command);
        boolean isErrorInProcessLogs = isErrorInProcessLogs(processLogs);
        if (isErrorInProcessLogs) {
            errors.add(repositoryPath + " " + hash + " " + processLogs);
        }
        return !isErrorInProcessLogs;
    }

    private static boolean isErrorInProcessLogs(List<String> processLogs) {
        return processLogs.stream()
                .anyMatch(processLog -> processLog.contains(GIT_ERROR));
    }

    public static CommitBasicInfo getCommitBasicInfo(String repositoryPath,
                                                     String hash) {
        List<String> command = List.of("git", "show", "-s", "--format=%ae%n%cd%n%B",
                "--date=format:%Y-%m-%d", hash);
        List<String> processLogs = ProcessExecutor.getProcessLogs(repositoryPath, command);
        return getCommitBasicInfo(hash, processLogs);
    }

    private static CommitBasicInfo getCommitBasicInfo(String hash,
                                                      List<String> processLogs) {
        String mail = getMail(processLogs);
        LocalDate date = getDate(processLogs);
        String message = getMessage(processLogs);
        return CommitBasicInfo.builder()
                .withHash(hash)
                .withMail(mail)
                .withDate(date)
                .withMessage(message)
                .build();
    }

    private static String getMail(List<String> processLogs) {
        return processLogs.get(0);
    }

    private static LocalDate getDate(List<String> processLogs) {
        return LocalDate.parse(processLogs.get(1));
    }

    private static String getMessage(List<String> processLogs) {
        return processLogs.get(2);
    }

}
