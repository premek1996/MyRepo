package domain;

public class Developer {

    private final String mail;
    private final int commits;

    private Developer(DeveloperBuilder builder) {
        this.mail = builder.mail;
        this.commits = builder.commits;
    }

    public static DeveloperBuilder builder() {
        return new DeveloperBuilder();
    }

    public String getMail() {
        return mail;
    }

    public int getCommits() {
        return commits;
    }

    @Override
    public String toString() {
        return "Developer{" +
                "mail='" + mail + '\'' +
                ", commits=" + commits +
                '}';
    }

    public static class DeveloperBuilder {

        private String mail;
        private int commits;

        public DeveloperBuilder withMail(String mail) {
            this.mail = mail;
            return this;
        }

        public DeveloperBuilder withCommits(int commits) {
            this.commits = commits;
            return this;
        }

        public Developer build() {
            return new Developer(this);
        }

    }

}
