package domain;

import java.time.LocalDate;

public class Commit {

    private final String hash;
    private final String message;
    private final Developer developer;
    private final int addedLines;
    private final int deletedLines;
    private final LocalDate date;

    private Commit(CommitBuilder builder) {
        this.hash = builder.hash;
        this.message = builder.message;
        this.developer = builder.developer;
        this.addedLines = builder.addedLines;
        this.deletedLines = builder.deletedLines;
        this.date = builder.date;
    }

    public static CommitBuilder builder() {
        return new CommitBuilder();
    }

    public String getHash() {
        return hash;
    }

    public String getMessage() {
        return message;
    }

    public Developer getDeveloper() {
        return developer;
    }

    public int getAddedLines() {
        return addedLines;
    }

    public int getDeletedLines() {
        return deletedLines;
    }

    public int getModifiedLines() {
        return addedLines + deletedLines;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Commit{" +
                "hash='" + hash + '\'' +
                ", message='" + message + '\'' +
                ", developer=" + developer +
                ", addedLines=" + addedLines +
                ", deletedLines=" + deletedLines +
                ", date=" + date +
                '}';
    }

    public static class CommitBuilder {

        private String hash;
        private String message;
        private Developer developer;
        private int addedLines;
        private int deletedLines;
        private LocalDate date;

        public CommitBuilder withHash(String hash) {
            this.hash = hash;
            return this;
        }

        public CommitBuilder withMessage(String message) {
            this.message = message;
            return this;
        }

        public CommitBuilder withDeveloper(Developer developer) {
            this.developer = developer;
            return this;
        }

        public CommitBuilder withAddedLines(int addedLines) {
            this.addedLines = addedLines;
            return this;
        }

        public CommitBuilder withDeletedLines(int deletedLines) {
            this.deletedLines = deletedLines;
            return this;
        }

        public CommitBuilder withDate(LocalDate date) {
            this.date = date;
            return this;
        }

        public Commit build() {
            return new Commit(this);
        }

    }

}
