package utils;

import domain.FileVersion;

import java.util.List;

public class FileVersionsDownloader {

    public static void downloadFileVersionsToDirectory(String directoryPath,
                                                       List<FileVersion> fileVersions) {
        DirectoryCreator.prepareDirectory(directoryPath);
        fileVersions.forEach(fileVersion -> downloadFileVersionToDirectory(directoryPath, fileVersion));
    }

    private static void downloadFileVersionToDirectory(String directoryPath,
                                                       FileVersion fileVersion) {
        List<String> command = List.of("wget", "-O", fileVersion.getSavedFileName(), fileVersion.getHttpAddress());
        ProcessExecutor.getProcessLogs(directoryPath, command);
    }

}
