package utils;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DirectoryCreator {

    private static final Logger LOGGER = Logger.getLogger(DirectoryCreator.class.getName());

    public static void prepareDirectory(String directoryPath) {
        File directory = new File(directoryPath);
        if (directory.exists()) {
            clearDirectory(directory);
        } else {
            createDirectory(directory);
        }
    }

    private static void clearDirectory(File directory) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                boolean isDeleted = file.delete();
                if (!isDeleted) {
                    if (LOGGER.isLoggable(Level.INFO)) {
                        LOGGER.info("Cannot delete the file: " + file.getAbsolutePath());
                    }
                }
            }
        }
    }

    private static void createDirectory(File directory) {
        boolean isCreated = directory.mkdir();
        if (!isCreated) {
            if (LOGGER.isLoggable(Level.INFO)) {
                LOGGER.info("Cannot create the directory: " + directory.getAbsolutePath());
            }
        }
    }

}
