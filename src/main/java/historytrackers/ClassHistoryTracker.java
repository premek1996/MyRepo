package historytrackers;

import domain.Commit;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ClassHistoryTracker {

    private final String repoPath;
    private final String filePath;
    private final List<Commit> commits;

    public ClassHistoryTracker(String repoPath, String filePath) {
        this.repoPath = repoPath;
        this.filePath = filePath;
        this.commits = getAllHashCommitsWhichChangedClass();
    }

    public List<Commit> getCommits() {
        return this.commits;
    }

    private List<Commit> getAllHashCommitsWhichChangedClass() {
        List<String> command = new ArrayList<>(Arrays.asList("git", "log", "--follow", "--oneline", "--", filePath));
        Process process = getExecutedProcess(command);
        List<String> processLogs = getProcessLogs(process);
        return mapProcessLogsToCommits(processLogs);
    }

    private Process getExecutedProcess(List<String> command) {
        Process process = null;
        try {
            process = new ProcessBuilder(command)
                    .directory(new File(repoPath))
                    .redirectErrorStream(true)
                    .start();
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return process;
    }

    private List<String> getProcessLogs(Process process) {
        List<String> processLogs = new ArrayList<>();
        BufferedReader reader =
                new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line = null;
        while (true) {
            try {
                if ((line = reader.readLine()) == null) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            processLogs.add(line);
        }
        return processLogs;
    }

    private List<Commit> mapProcessLogsToCommits(List<String> processLogs) {
        return processLogs.stream().map(this::getCommitFromProcessLog).collect(Collectors.toList());
    }

    private Commit getCommitFromProcessLog(String processLog) {
        String hash = processLog.split(" ")[0];
        return new Commit(hash, "committer");
    }

}
