package domain;

public class FileVersion {

    private final String hash;
    private final String fileURI;
    private final String filePathInSavedDirectory;
    private final String savedFileName;

    private FileVersion(FileVersionBuilder fileVersionBuilder) {
        this.hash = fileVersionBuilder.hash;
        this.fileURI = fileVersionBuilder.fileURI;
        this.filePathInSavedDirectory = fileVersionBuilder.filePathInSavedDirectory;
        this.savedFileName = fileVersionBuilder.savedFileName;
    }

    public static FileVersionBuilder builder() {
        return new FileVersionBuilder();
    }

    public String getHash() {
        return hash;
    }

    public String getFileURI() {
        return fileURI;
    }

    public String getFilePathInSavedDirectory() {
        return filePathInSavedDirectory;
    }

    public String getSavedFileName() {
        return savedFileName;
    }

    @Override
    public String toString() {
        return "FileVersion{" +
                "hash='" + hash + '\'' +
                ", fileURI='" + fileURI + '\'' +
                ", filePathInSavedDirectory='" + filePathInSavedDirectory + '\'' +
                ", savedFileName='" + savedFileName + '\'' +
                '}';
    }

    public static class FileVersionBuilder {

        private String hash;
        private String fileURI;
        private String filePathInSavedDirectory;
        private String savedFileName;

        public FileVersionBuilder withHash(String hash) {
            this.hash = hash;
            return this;
        }

        public FileVersionBuilder withFileURI(String fileURI) {
            this.fileURI = fileURI;
            return this;
        }

        public FileVersionBuilder withFilePathInSavedDirectory(String filePathInSavedDirectory) {
            this.filePathInSavedDirectory = filePathInSavedDirectory;
            return this;
        }

        public FileVersionBuilder withSavedFileName(String savedFileName) {
            this.savedFileName = savedFileName;
            return this;
        }

        public FileVersion build() {
            return new FileVersion(this);
        }

    }

}
