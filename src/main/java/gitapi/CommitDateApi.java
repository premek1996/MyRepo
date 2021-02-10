package gitapi;

import utils.ProcessExecutor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class CommitDateApi {

    public static Date getCommitDate(String repoPath, String hash) {
        List<String> command = new ArrayList<>(Arrays.asList("git", "show", "-s", "--format=%ci", hash));
        List<String> processLogs = ProcessExecutor.getProcessLogs(repoPath, command);
        return mapProcessLogsToCommitDate(processLogs);
    }

    private static Date mapProcessLogsToCommitDate(List<String> processLogs) {
        String processLog = processLogs.get(0);
        String date = processLog.split(" ")[0];
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date commitDate = null;
        try {
            commitDate = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return commitDate;
    }

}
