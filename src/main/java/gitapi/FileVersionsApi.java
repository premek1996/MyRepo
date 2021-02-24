package gitapi;

import domain.FileVersion;
import utils.ProcessExecutor;

import java.text.MessageFormat;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileVersionsApi {

    private static final String DIRECTORY_NAME = "file-versions";

    public static List<FileVersion> getDownloadedFileVersions(String repositoryPath,
                                                              String filePath) {
        List<String> command = List.of("git", "log", "--follow", "--name-only", "--oneline", filePath);
        List<String> processLogs = ProcessExecutor.getProcessLogs(repositoryPath, command);
        String directoryPath = getDirectoryPath(repositoryPath);
        List<FileVersion> fileVersions = getFileVersionsToDownload(directoryPath, processLogs);
        downloadFileVersions(repositoryPath, fileVersions);
        return fileVersions;
    }

    private static String getDirectoryPath(String repositoryPath) {
        return repositoryPath + "\\" + DIRECTORY_NAME;
    }

    private static List<FileVersion> getFileVersionsToDownload(String directoryPath,
                                                               List<String> processLogs) {
        int fileVersionsNumber = processLogs.size() / 2;
        return Stream.iterate(0, index -> index < fileVersionsNumber, index -> index + 1)
                .map(toFileVersion(directoryPath, processLogs))
                .collect(Collectors.toList());
    }

    private static Function<Integer, FileVersion> toFileVersion(String directoryPath,
                                                                List<String> processLogs) {
        return index -> {
            String hash = getHash(processLogs.get(index * 2));
            String filePathInRepository = processLogs.get(index * 2 + 1);
            String savedFileName = hash + ".java";
            String filePathInSavedDirectory = directoryPath + "\\" + savedFileName;
            return FileVersion.builder()
                    .withHash(hash)
                    .withFilePathInRepository(filePathInRepository)
                    .withSavedFileName(savedFileName)
                    .withFilePathInSavedDirectory(filePathInSavedDirectory)
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
                fileVersion.getFilePathInRepository());
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
