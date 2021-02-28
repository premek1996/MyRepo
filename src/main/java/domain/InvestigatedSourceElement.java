package domain;

import gitapi.CommitBasicInfoApi;
import gitapi.DevelopersApi;

import java.time.LocalDate;
import java.util.List;

public class InvestigatedSourceElement {

    private final String repositoryPath;
    private final List<Developer> repositoryDevelopers;
    private final String filePath;
    private final int startLine;
    private final int endLine;
    private final String currentHashCommit;
    private final LocalDate currentDate;
    private List<Commit> commits;

    public InvestigatedSourceElement(String repositoryPath,
                                     String filePath,
                                     int startLine,
                                     int endLine,
                                     String currentHashCommit) {
        this.repositoryPath = repositoryPath;
        this.filePath = filePath;
        this.startLine = startLine;
        this.endLine = endLine;
        this.currentHashCommit = currentHashCommit;
        this.currentDate = determineCurrentDate();
        this.repositoryDevelopers = determineRepositoryDevelopers();
    }

    private InvestigatedSourceElement(InvestigatedSourceElementBuilder builder) {
        this.repositoryPath = builder.repositoryPath;
        this.filePath = builder.filePath;
        this.startLine = builder.startLine;
        this.endLine = builder.endLine;
        this.currentHashCommit = builder.currentHashCommit;
        this.currentDate = determineCurrentDate();
        this.repositoryDevelopers = determineRepositoryDevelopers();
    }

    public static InvestigatedSourceElementBuilder builder() {
        return new InvestigatedSourceElementBuilder();
    }

    private LocalDate determineCurrentDate() {
        return CommitBasicInfoApi.getCommitBasicInfo(repositoryPath, currentHashCommit).getDate();
    }

    private List<Developer> determineRepositoryDevelopers() {
        return DevelopersApi.getDevelopers(repositoryPath);
    }

    public String getRepositoryPath() {
        return repositoryPath;
    }

    public String getFilePath() {
        return filePath;
    }

    public List<Developer> getRepositoryDevelopers() {
        return repositoryDevelopers;
    }

    public int getStartLine() {
        return startLine;
    }

    public int getEndLine() {
        return endLine;
    }

    public String getCurrentHashCommit() {
        return currentHashCommit;
    }

    public Developer getDeveloper(String mail) {
        return repositoryDevelopers.stream()
                .filter(developer -> developer.getMail().equals(mail))
                .findAny()
                .orElse(null);
    }

    public LocalDate getCurrentDate() {
        return currentDate;
    }

    public void setCommits(List<Commit> commits) {
        this.commits = commits;
    }

    public List<Commit> getCommits() {
        return commits;
    }

    @Override
    public String toString() {
        return "InvestigatedSourceElement{" +
                "repositoryPath='" + repositoryPath + '\'' +
                ", repositoryDevelopers=" + repositoryDevelopers +
                ", filePath='" + filePath + '\'' +
                ", startLine=" + startLine +
                ", endLine=" + endLine +
                ", currentHashCommit='" + currentHashCommit + '\'' +
                ", currentDate=" + currentDate +
                '}';
    }

    public static class InvestigatedSourceElementBuilder {

        private String repositoryPath;
        private String filePath;
        private int startLine;
        private int endLine;
        private String currentHashCommit;

        public InvestigatedSourceElementBuilder withRepositoryPath(String repositoryPath) {
            this.repositoryPath = repositoryPath;
            return this;
        }

        public InvestigatedSourceElementBuilder withFilePath(String filePath) {
            this.filePath = filePath;
            return this;
        }

        public InvestigatedSourceElementBuilder withStartLine(int startLine) {
            this.startLine = startLine;
            return this;
        }

        public InvestigatedSourceElementBuilder withEndLine(int endLine) {
            this.endLine = endLine;
            return this;
        }

        public InvestigatedSourceElementBuilder withCurrentHashCommit(String currentHashCommit) {
            this.currentHashCommit = currentHashCommit;
            return this;
        }

        public InvestigatedSourceElement build() {
            return new InvestigatedSourceElement(this);
        }

    }

}
