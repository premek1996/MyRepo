package utils;

import domain.FileVersion;

import java.io.File;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileVersionsDownloader {

    private static final Logger LOGGER = Logger.getLogger(FileVersionsDownloader.class.getName());

    public static void downloadFileVersionsToDirectory(String directoryPath,
                                                       List<FileVersion> fileVersions) {
        createDirectory(directoryPath);
        fileVersions.forEach(fileVersion -> downloadFileVersionToDirectory(directoryPath, fileVersion));
    }

    private static void createDirectory(String directoryPath) {
        File directory = new File(directoryPath);
        boolean isCreated = directory.mkdir();
        if (!isCreated) {
            if (LOGGER.isLoggable(Level.INFO)) {
                LOGGER.info("Cannot create a directory: " + directoryPath);
            }
        }
    }

    private static void downloadFileVersionToDirectory(String directoryPath,
                                                       FileVersion fileVersion) {
        List<String> command = List.of("wget", "-O", fileVersion.getSavedFileName(), fileVersion.getHttpAddress());
        ProcessExecutor.getProcessLogs(directoryPath, command);
    }

}
