package domain;

public class SourceElementModification {

    private final String hash;
    private final int addedLines;
    private final int deletedLines;

    private SourceElementModification(String hash,
                                      int addedLines,
                                      int deletedLines) {
        this.hash = hash;
        this.addedLines = addedLines;
        this.deletedLines = deletedLines;
    }

    public static SourceElementModificationBuilder builder() {
        return new SourceElementModificationBuilder();
    }

    public String getHash() {
        return hash;
    }

    public int getAddedLines() {
        return addedLines;
    }

    public int getDeletedLines() {
        return deletedLines;
    }

    @Override
    public String toString() {
        return "SourceElementModification{" +
                "hash='" + hash + '\'' +
                ", addedLines=" + addedLines +
                ", deletedLines=" + deletedLines +
                '}';
    }

    public static class SourceElementModificationBuilder {

        private String hash;
        private int addedLines;
        private int deletedLines;

        public SourceElementModificationBuilder withHash(String hash) {
            this.hash = hash;
            return this;
        }

        public SourceElementModificationBuilder withAddedLines(int addedLines) {
            this.addedLines = addedLines;
            return this;
        }

        public SourceElementModificationBuilder withDeletedLines(int deletedLines) {
            this.deletedLines = deletedLines;
            return this;
        }

        public SourceElementModification build() {
            return new SourceElementModification(hash, addedLines, deletedLines);
        }

    }

}
