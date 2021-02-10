package domain;

public class Developer {

    private final String mail;
    private final int commitsNumber;

    private Developer(String mail, int commitsNumber) {
        this.mail = mail;
        this.commitsNumber = commitsNumber;
    }

    public static DeveloperBuilder builder() {
        return new DeveloperBuilder();
    }

    public String getMail() {
        return mail;
    }

    public int getCommitsNumber() {
        return commitsNumber;
    }

    @Override
    public String toString() {
        return "Developer{" +
                "mail='" + mail + '\'' +
                ", commitsNumber=" + commitsNumber +
                '}';
    }

    public static class DeveloperBuilder {

        private String mail;
        private int commitsNumber;

        public DeveloperBuilder withMail(String mail) {
            this.mail = mail;
            return this;
        }

        public DeveloperBuilder withCommitsNumber(int commitsNumber) {
            this.commitsNumber = commitsNumber;
            return this;
        }

        public Developer build() {
            return new Developer(mail, commitsNumber);
        }

    }

}
