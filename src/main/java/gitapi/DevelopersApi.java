package gitapi;

import domain.Developer;
import utils.ProcessExecutor;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DevelopersApi {

    private DevelopersApi() {
    }

    public static List<Developer> getDevelopers(String repositoryPath) {
        List<String> command = List.of("git", "shortlog", "--summary", "--numbered", "--all");
        List<String> processLogs = ProcessExecutor.getProcessLogs(repositoryPath, command);
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
        String name = getName(processLogElements);
        return Developer.builder()
                .withCommits(commits)
                .withName(name)
                .build();
    }

    private static List<String> getProcessLogElements(String processLog) {
        processLog = processLog.replaceFirst("\\s+", "");
        return Arrays.asList(processLog.split("\\s+"));
    }

    private static int getCommits(List<String> processLogElements) {
        return Integer.parseInt(processLogElements.get(0));
    }

    private static String getName(List<String> processLogElements) {
        return String.join(" ", processLogElements.subList(1, processLogElements.size()));
    }

}
