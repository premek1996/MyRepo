package domain;

public class Developer {

    private final String name;
    private final int commits;

    private Developer(DeveloperBuilder builder) {
        this.name = builder.name;
        this.commits = builder.commits;
    }

    public static DeveloperBuilder builder() {
        return new DeveloperBuilder();
    }

    public String getName() {
        return name;
    }

    public int getCommits() {
        return commits;
    }

    @Override
    public String toString() {
        return "Developer{" +
                "name='" + name + '\'' +
                ", commits=" + commits +
                '}';
    }

    public static class DeveloperBuilder {

        private String name;
        private int commits;

        public DeveloperBuilder withName(String name) {
            this.name = name;
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
