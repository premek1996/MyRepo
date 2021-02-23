package domain;

public class FileVersion {

    private final String hash;
    private final String filePath;

    private FileVersion(FileVersionBuilder fileVersionBuilder) {
        this.hash = fileVersionBuilder.hash;
        this.filePath = fileVersionBuilder.filePath;
    }

    public static FileVersionBuilder builder() {
        return new FileVersionBuilder();
    }

    public String getHash() {
        return hash;
    }

    public String getFilePath() {
        return filePath;
    }

    @Override
    public String toString() {
        return "FileVersion{" +
                "hash='" + hash + '\'' +
                ", filePath='" + filePath + '\'' +
                '}';
    }

    public static class FileVersionBuilder {

        private String hash;
        private String filePath;

        public FileVersionBuilder withHash(String hash) {
            this.hash = hash;
            return this;
        }

        public FileVersionBuilder withFilePath(String filePath) {
            this.filePath = filePath;
            return this;
        }

        public FileVersion build() {
            return new FileVersion(this);
        }

    }

}
