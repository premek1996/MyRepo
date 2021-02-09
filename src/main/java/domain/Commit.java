package domain;

public class Commit {

    private final String hash;
    private final String committer;

    public Commit(String hash, String committer) {
        this.hash = hash;
        this.committer = committer;
    }

    public String getHash() {
        return hash;
    }

    public String getCommitter() {
        return committer;
    }

    @Override
    public String toString() {
        return "Commit{" +
                "hash='" + hash + '\'' +
                ", committer='" + committer + '\'' +
                '}';
    }
}
