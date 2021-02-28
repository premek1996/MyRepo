package gitapi;

import domain.InvestigatedSourceElement;
import utils.ProcessExecutor;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SourceElementFragmentationApi {

    private SourceElementFragmentationApi() {
    }

    private final static Pattern PATTERN = Pattern.compile("<.*>");

    public static long getSourceElementFragmentationAcrossDevelopers(InvestigatedSourceElement investigatedSourceElement) {
        int startLine = investigatedSourceElement.getStartLine();
        int endLine = investigatedSourceElement.getEndLine();

        String filePath = investigatedSourceElement.getFilePath();
        String repositoryPath = investigatedSourceElement.getRepositoryPath();
        String range = startLine + "," + endLine;

        List<String> command = List.of("git", "blame", "--show-email", "-L", range, filePath);
        List<String> processLogs = ProcessExecutor.getProcessLogs(repositoryPath, command);

        return getSourceElementFragmentationAcrossDevelopers(processLogs);
    }

    private static long getSourceElementFragmentationAcrossDevelopers(List<String> processLogs) {
        return processLogs.stream()
                .map(SourceElementFragmentationApi::getMail)
                .distinct()
                .count();
    }

    private static String getMail(String processLog) {
        return Optional.of(PATTERN.matcher(processLog))
                .filter(Matcher::find)
                .map(matcher -> matcher.group().replaceAll("[<>]", ""))
                .orElse("");
    }

}
