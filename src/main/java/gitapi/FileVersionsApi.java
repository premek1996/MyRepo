package gitapi;

import domain.FileVersion;
import utils.ProcessExecutor;

import java.text.MessageFormat;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileVersionsApi {

    public static void downloadFileVersions(String repositoryPath,
                                            String filePath) {
        List<String> command = List.of("git", "log", "--follow", "--name-only", "--oneline", filePath);
        List<String> processLogs = ProcessExecutor.getProcessLogs(repositoryPath, command);
        List<FileVersion> fileVersions = getFileVersions(processLogs);
        downloadFileVersions(repositoryPath, fileVersions);
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

    private static void downloadFileVersion(String repositoryPath, FileVersion fileVersion) {
        String downloadedFileName = fileVersion.getHash() + ".java";
        String downloadedFileHttpAddress = getDownloadedFileHttpAddress(repositoryPath, fileVersion);
        List<String> command = List.of("wget", "-O", downloadedFileName, downloadedFileHttpAddress);
        ProcessExecutor.getProcessLogs(repositoryPath, command);
    }

    private static String getDownloadedFileHttpAddress(String repositoryPath, FileVersion fileVersion) {
        String user = getUser(repositoryPath);
        String project = getProject(repositoryPath);
        return MessageFormat.format("https://raw.githubusercontent.com/{0}/{1}/{2}/{3}",
                user,
                project,
                fileVersion.getHash(),
                fileVersion.getFilePath());
    }

    private static String getUser(String repositoryPath) {
        String[] repositoryPathElements = repositoryPath.split("\\\\");
        return repositoryPathElements[repositoryPathElements.length - 2];
    }

    private static String getProject(String repositoryPath) {
        String[] repositoryPathElements = repositoryPath.split("\\\\");
        return repositoryPathElements[repositoryPathElements.length - 1];
    }

}
