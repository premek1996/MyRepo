package gitapi;

import domain.CommitBasicInfo;
import utils.ProcessExecutor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class CommitBasicInfoApi {

    public static CommitBasicInfo getCommitBasicInfo(String repoPath, String hash) {
        List<String> command = new ArrayList<>
                (Arrays.asList("git", "show", "-s", "--format=%ae%n%cd%n%B", "--date=format:%Y-%m-%d", hash));
        List<String> processLogs = ProcessExecutor.getProcessLogs(repoPath, command);
        return getCommitBasicInfo(hash, processLogs);
    }

    private static CommitBasicInfo getCommitBasicInfo(String hash, List<String> processLogs) {
        String mail = getMail(processLogs);
        Date date = getDate(processLogs);
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

    private static Date getDate(List<String> processLogs) {
        String date = processLogs.get(1);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date commitDate = null;
        try {
            commitDate = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return commitDate;
    }

    private static String getMessage(List<String> processLogs) {
        return processLogs.get(2);
    }

}
