package domain;

import java.time.LocalDate;

public class CommitBasicInfo {

    private final String hash;
    private final String mail;
    private final String message;
    private final LocalDate date;

    private CommitBasicInfo(CommitBasicInfoBuilder builder) {
        this.hash = builder.hash;
        this.mail = builder.mail;
        this.message = builder.message;
        this.date = builder.date;
    }

    public static CommitBasicInfoBuilder builder() {
        return new CommitBasicInfoBuilder();
    }

    public String getHash() {
        return hash;
    }

    public String getMail() {
        return mail;
    }

    public String getMessage() {
        return message;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "CommitBasicInfo{" +
                "hash='" + hash + '\'' +
                ", mail='" + mail + '\'' +
                ", message='" + message + '\'' +
                ", date=" + date +
                '}';
    }

    public static class CommitBasicInfoBuilder {

        private String hash;
        private String mail;
        private String message;
        private LocalDate date;

        public CommitBasicInfoBuilder withHash(String hash) {
            this.hash = hash;
            return this;
        }

        public CommitBasicInfoBuilder withMail(String mail) {
            this.mail = mail;
            return this;
        }

        public CommitBasicInfoBuilder withMessage(String message) {
            this.message = message;
            return this;
        }

        public CommitBasicInfoBuilder withDate(LocalDate date) {
            this.date = date;
            return this;
        }

        public CommitBasicInfo build() {
            return new CommitBasicInfo(this);
        }

    }

}
