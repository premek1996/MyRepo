package domain;

import java.util.Date;

public class CommitBasicInfo {

    private final String hash;
    private final String mail;
    private final String message;
    private final Date date;

    private CommitBasicInfo(String hash, String mail, String message, Date date) {
        this.hash = hash;
        this.mail = mail;
        this.message = message;
        this.date = date;
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

    public Date getDate() {
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
        private Date date;

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

        public CommitBasicInfoBuilder withDate(Date date) {
            this.date = date;
            return this;
        }

        public CommitBasicInfo build() {
            return new CommitBasicInfo(hash, mail, message, date);
        }

    }

}
