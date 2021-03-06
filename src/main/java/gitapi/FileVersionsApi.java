package gitapi;

import domain.FileVersion;
import utils.FileVersionsDownloader;
import utils.ProcessExecutor;

import java.text.MessageFormat;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileVersionsApi {

    private static final String DIRECTORY_NAME = "file-versions";

    private FileVersionsApi() {
    }

    public static List<FileVersion> getDownloadedFileVersions(String repositoryPath,
                                                              String filePath) {
        List<String> command = List.of("git", "log", "--follow", "--name-only", "--oneline", filePath);
        List<String> processLogs = ProcessExecutor.getProcessLogs(repositoryPath, command);
        String directoryPath = getDirectoryPath(repositoryPath);
        List<FileVersion> fileVersions = getFileVersionsToDownload(repositoryPath, directoryPath, processLogs);
        FileVersionsDownloader.downloadFileVersionsToDirectory(directoryPath, fileVersions);
        return fileVersions;
    }

    private static String getDirectoryPath(String repositoryPath) {
        return repositoryPath + "\\" + DIRECTORY_NAME;
    }

    private static List<FileVersion> getFileVersionsToDownload(String repositoryPath,
                                                               String directoryPath,
                                                               List<String> processLogs) {
        int fileVersionsNumber = processLogs.size() / 2;
        return Stream.iterate(0, index -> index < fileVersionsNumber, index -> index + 1)
                .map(toFileVersion(repositoryPath, directoryPath, processLogs))
                .collect(Collectors.toList());
    }

    private static Function<Integer, FileVersion> toFileVersion(String repositoryPath,
                                                                String directoryPath,
                                                                List<String> processLogs) {
        return index -> {
            String hash = getHash(processLogs.get(index * 2));
            String filePathInRepository = processLogs.get(index * 2 + 1);
            String fileURI = getDownloadedFileURI(repositoryPath, hash, filePathInRepository);
            String savedFileName = hash + ".java";
            String filePathInSavedDirectory = directoryPath + "\\" + savedFileName;
            return FileVersion.builder()
                    .withHash(hash)
                    .withFileURI(fileURI)
                    .withSavedFileName(savedFileName)
                    .withFilePathInSavedDirectory(filePathInSavedDirectory)
                    .build();
        };
    }

    private static String getHash(String processLog) {
        String[] processLogElements = processLog.split("\\s+");
        return processLogElements[0];
    }

    private static String getDownloadedFileURI(String repositoryPath,
                                               String hash,
                                               String filePathInRepository) {
        String user = getUser(repositoryPath);
        String project = getProject(repositoryPath);
        return MessageFormat.format("https://raw.githubusercontent.com/{0}/{1}/{2}/{3}",
                user,
                project,
                hash,
                filePathInRepository);
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
