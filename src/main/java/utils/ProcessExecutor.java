package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ProcessExecutor {

    public static List<String> getProcessLogs(String repoPath, List<String> command) {
        Process process = getExecutedProcess(repoPath, command);
        return getProcessLogs(process);
    }

    private static Process getExecutedProcess(String repoPath, List<String> command) {
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

    private static List<String> getProcessLogs(Process process) {
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

}
