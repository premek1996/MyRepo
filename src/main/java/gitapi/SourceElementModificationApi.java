package gitapi;


import domain.SourceElementModification;
import utils.ProcessExecutor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//git show --numstat --format= 114c412afbfba24ffb4fbc804e5308a823a16a78

public class SourceElementModificationApi {

    public static SourceElementModification getInvestigatedSourceElementModification
            (String repoPath, String filePath, String hash) {
        List<String> command = new ArrayList<>
                (Arrays.asList("git", "show", "--numstat", "--format=", hash, filePath));
        List<String> processLogs = ProcessExecutor.getProcessLogs(repoPath, command);
        return getSourceElementModification(processLogs.get(0));
    }

    private static SourceElementModification getSourceElementModification(String processLog) {
        List<String> processLogElements = getProcessLogElements(processLog);
        int addedLines = getAddedLines(processLogElements);
        int deletedLines = getDeletedLines(processLogElements);
        String previousFilePath = getPreviousFilePath(processLogElements);
        System.out.println(previousFilePath);

        return new SourceElementModification("",
                "",
                addedLines,
                deletedLines);
    }

    private static List<String> getProcessLogElements(String processLog) {
        return Arrays.asList(processLog.split("\\s+"));
    }

    private static int getAddedLines(List<String> processLogs) {
        return Integer.parseInt(processLogs.get(0));
    }

    private static int getDeletedLines(List<String> processLogs) {
        return Integer.parseInt(processLogs.get(1));
    }

    private static String getPreviousFilePath(List<String> processLogs) {
        return processLogs.get(2);
    }

}
