package domain;

public class Commit {

    private final String hash;
    private final Developer developer;

    private Commit(String hash, Developer developer) {
        this.hash = hash;
        this.developer = developer;
    }

    public static CommitBuilder builder() {
        return new CommitBuilder();
    }

    public String getHash() {
        return hash;
    }

    public Developer getDeveloper() {
        return developer;
    }

    public static class CommitBuilder {

        private String hash;
        private Developer developer;

        public CommitBuilder withHash(String hash) {
            this.hash = hash;
            return this;
        }

        public CommitBuilder withCommitter(Developer developer) {
            this.developer = developer;
            return this;
        }

        public Commit build() {
            return new Commit(hash, developer);
        }

    }

}
