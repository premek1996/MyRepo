package gitapi;

import domain.InvestigatedSourceElement;
import utils.ProcessExecutor;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SourceElementFragmentationApi {

    public static long getSourceElementFragmentationAcrossDevelopers
            (InvestigatedSourceElement investigatedSourceElement) {
        int startLine = investigatedSourceElement.getStartLine();
        int endLine = investigatedSourceElement.getEndLine();
        String filePath = investigatedSourceElement.getFilePath();
        String repoPath = investigatedSourceElement.getRepositoryPath();
        List<String> command = List.of("git", "blame", "--show-email", "-L",
                startLine + "," + endLine, filePath);
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
        String mail = "";
        Pattern pattern = Pattern.compile("<.*>");
        Matcher matcher = pattern.matcher(processLog);
        if (matcher.find()) {
            mail = matcher.group().replaceAll("[<>]", "");
        }
        return mail;
    }

}
