package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ProcessExecutor {

    public static List<String> getProcessLogs(String repoPath, List<String> command) {
        List<String> processLogs = new ArrayList<>();
        try {
            Process process = new ProcessBuilder(command)
                    .directory(new File(repoPath))
                    .redirectErrorStream(true)
                    .start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                processLogs.add(line);
            }
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return processLogs;
    }

}
