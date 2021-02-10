package domain;

public class Committer {

    private final String mail;
    private final int commitsNumber;

    private Committer(String mail, int commitsNumber) {
        this.mail = mail;
        this.commitsNumber = commitsNumber;
    }

    public static CommitterBuilder builder() {
        return new CommitterBuilder();
    }

    public String getMail() {
        return mail;
    }

    public int getCommitsNumber() {
        return commitsNumber;
    }

    @Override
    public String toString() {
        return "Committer{" +
                "mail='" + mail + '\'' +
                ", commitsNumber=" + commitsNumber +
                '}';
    }

    public static class CommitterBuilder {

        private String mail;
        private int commitsNumber;

        public CommitterBuilder withMail(String mail) {
            this.mail = mail;
            return this;
        }

        public CommitterBuilder withCommitsNumber(int commitsNumber) {
            this.commitsNumber = commitsNumber;
            return this;
        }

        public Committer build() {
            return new Committer(mail, commitsNumber);
        }

    }

}
