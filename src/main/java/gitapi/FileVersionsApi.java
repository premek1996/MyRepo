package gitapi;

import domain.FileVersion;
import utils.ProcessExecutor;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileVersionsApi {

    public static void downloadFileVersions(String repoPath,
                                            String filePath) {
        List<String> command = List.of("git", "log", "--follow", "--name-only", "--oneline", filePath);
        List<String> processLogs = ProcessExecutor.getProcessLogs(repoPath, command);
        List<FileVersion> fileVersions = getFileVersions(processLogs);
        downloadFileVersions(repoPath, fileVersions);
    }

    private static List<FileVersion> getFileVersions(List<String> processLogs) {
        int fileVersionsNumber = processLogs.size() / 2;
        return Stream.iterate(0, index -> index < fileVersionsNumber, index -> index + 1)
                .map(toFileVersion(processLogs))
                .collect(Collectors.toList());
    }

    private static Function<Integer, FileVersion> toFileVersion(List<String> processLogs) {
        return index -> {
            String hash = getHash(processLogs.get(index * 2));
            String filePath = processLogs.get(index * 2 + 1);
            return FileVersion.builder()
                    .withHash(hash)
                    .withFilePath(filePath)
                    .build();
        };
    }

    private static String getHash(String processLog) {
        String[] processLogElements = processLog.split("\\s+");
        return processLogElements[0];
    }

    private static void downloadFileVersions(String repoPath, List<FileVersion> fileVersions) {
        fileVersions.forEach(fileVersion -> downloadFileVersion(repoPath, fileVersion));
    }

    private static void downloadFileVersion(String repoPath, FileVersion fileVersion) {
        String savedFileName = fileVersion.getHash() + ".java";
        String httpAddress = "https://raw.githubusercontent.com/apache/syncope/" + fileVersion.getHash() + "/" + fileVersion.getFilePath();
        List<String> command = List.of("wget", "-O", savedFileName, httpAddress);
        ProcessExecutor.getProcessLogs(repoPath, command);
    }

}
