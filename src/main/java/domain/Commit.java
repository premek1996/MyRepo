package domain;

public class Commit {

    private final String hash;
    private final Committer committer;

    private Commit(String hash, Committer committer) {
        this.hash = hash;
        this.committer = committer;
    }

    public static CommitBuilder builder() {
        return new CommitBuilder();
    }

    public String getHash() {
        return hash;
    }

    public Committer getCommitter() {
        return committer;
    }

    public static class CommitBuilder {

        private String hash;
        private Committer committer;

        public CommitBuilder withHash(String hash) {
            this.hash = hash;
            return this;
        }

        public CommitBuilder withCommitter(Committer committer) {
            this.committer = committer;
            return this;
        }

        public Commit build() {
            return new Commit(hash, committer);
        }

    }

}
