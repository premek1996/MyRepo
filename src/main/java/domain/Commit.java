package domain;

import java.util.Date;

public class Commit {

    private final String hash;
    private final String message;
    private final Developer developer;
    private final int addedLinesNumber;
    private final int deletedLinesNumber;
    private final Date date;

    private Commit(String hash,
                   String message,
                   Developer developer,
                   int addedLinesNumber,
                   int deletedLinesNumber,
                   Date date) {
        this.hash = hash;
        this.message = message;
        this.developer = developer;
        this.addedLinesNumber = addedLinesNumber;
        this.deletedLinesNumber = deletedLinesNumber;
        this.date = date;
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

    public int getAddedLinesNumber() {
        return addedLinesNumber;
    }

    public int getDeletedLinesNumber() {
        return deletedLinesNumber;
    }

    public int getModifiedLinesNumber() {
        return addedLinesNumber + deletedLinesNumber;
    }

    public Date getDate() {
        return date;
    }

    public static class CommitBuilder {

        private String hash;
        private String message;
        private Developer developer;
        private int addedLinesNumber;
        private int deletedLinesNumber;
        private Date date;

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

        public CommitBuilder withAddedLinesNumber(int addedLinesNumber) {
            this.addedLinesNumber = addedLinesNumber;
            return this;
        }

        public CommitBuilder withDeletedLinesNumber(int deletedLinesNumber) {
            this.deletedLinesNumber = deletedLinesNumber;
            return this;
        }

        public CommitBuilder withDate(Date date) {
            this.date = date;
            return this;
        }

        public Commit build() {
            return new Commit(hash, message, developer, addedLinesNumber, deletedLinesNumber, date);
        }

    }

}
