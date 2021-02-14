package gitapi;

import domain.InvestigatedSourceElement;
import utils.ProcessExecutor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SourceElementFragmentationApi {

    public static long getSourceElementFragmentationAcrossDevelopers
            (InvestigatedSourceElement investigatedSourceElement) {
        int startLine = investigatedSourceElement.getStartLine();
        int endLine = investigatedSourceElement.getEndLine();
        String filePath = investigatedSourceElement.getFilePath();
        String repoPath = investigatedSourceElement.getRepoPath();
        List<String> command = new ArrayList<>
                (Arrays.asList("git", "blame", "--show-email", "-L",
                        startLine + "," + endLine, filePath));
        List<String> processLogs = ProcessExecutor.getProcessLogs(repoPath, command);
        return getSourceElementFragmentationAcrossDevelopers(processLogs);
    }

    private static long getSourceElementFragmentationAcrossDevelopers(List<String> processLogs) {
        return processLogs.stream()
                .map(SourceElementFragmentationApi::getMail)
                .distinct()
                .count();
    }

    private static String getMail(String processLog) {
        String[] processLogElements = processLog.split("\\s+");
        String mail = processLogElements[2];
        return mail.replaceAll("[(<>]", "");
    }

}
