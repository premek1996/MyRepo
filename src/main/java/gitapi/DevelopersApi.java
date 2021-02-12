package gitapi;

import domain.Developer;
import utils.ProcessExecutor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DevelopersApi {

    public static List<Developer> getDevelopers(String repoPath) {
        List<String> command = new ArrayList<>
                (Arrays.asList("git", "shortlog", "--summary", "--numbered", "--email", "--all"));
        List<String> processLogs = ProcessExecutor.getProcessLogs(repoPath, command);
        return getDevelopers(processLogs);
    }

    private static List<Developer> getDevelopers(List<String> processLogs) {
        return processLogs.stream()
                .map(DevelopersApi::getDeveloper)
                .collect(Collectors.toList());
    }

    private static Developer getDeveloper(String processLog) {
        List<String> processLogElements = getProcessLogElements(processLog);
        int commits = getCommits(processLogElements);
        String mail = getMail(processLogElements);
        return Developer.builder()
                .withCommits(commits)
                .withMail(mail)
                .build();
    }

    private static List<String> getProcessLogElements(String processLog) {
        processLog = processLog.replaceFirst("\\s+", "");
        return Arrays.asList(processLog.split("\\s+"));
    }

    private static int getCommits(List<String> processLogElements) {
        return Integer.parseInt(processLogElements.get(0));
    }

    private static String getMail(List<String> processLogElements) {
        String mail = processLogElements.get(processLogElements.size() - 1);
        return mail.replaceAll("[<>]", "");
    }

}
