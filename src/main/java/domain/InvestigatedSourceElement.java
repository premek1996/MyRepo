package domain;

import gitapi.CommitBasicInfoApi;
import gitapi.DevelopersApi;

import java.time.LocalDate;
import java.util.List;

public abstract class InvestigatedSourceElement {

    protected final String repositoryURI;
    protected final String repositoryPath;
    protected final String filePath;
    protected final int startLine;
    protected final int endLine;
    protected final String currentHashCommit;
    protected LocalDate currentDate;
    protected List<Developer> repositoryDevelopers;
    protected List<Commit> commits;

    public InvestigatedSourceElement(String repositoryURI,
                                     String repositoryPath,
                                     String filePath,
                                     int startLine,
                                     int endLine,
                                     String currentHashCommit) {
        this.repositoryURI = repositoryURI;
        this.repositoryPath = repositoryPath;
        this.filePath = filePath;
        this.startLine = startLine;
        this.endLine = endLine;
        this.currentHashCommit = currentHashCommit;
        this.currentDate = determineCurrentDate();
        this.repositoryDevelopers = determineRepositoryDevelopers();
        this.commits = determineCommits();
    }

    private LocalDate determineCurrentDate() {
        return CommitBasicInfoApi.getCommitBasicInfo(repositoryPath, currentHashCommit).getDate();
    }

    private List<Developer> determineRepositoryDevelopers() {
        return DevelopersApi.getDevelopers(repositoryPath);
    }

    protected abstract List<Commit> determineCommits();

    public abstract String getClassName();

    public abstract String getMethodName();

    public String getRepositoryURI() {
        return repositoryURI;
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

    public Developer getDeveloper(String name) {
        return repositoryDevelopers.stream()
                .filter(developer -> developer.getName().equals(name))
                .findAny()
                .orElse(null);
    }

    public LocalDate getCurrentDate() {
        return currentDate;
    }

    public List<Commit> getCommits() {
        return commits;
    }

    @Override
    public String toString() {
        return "InvestigatedSourceElement{" +
                "repositoryURI='" + repositoryURI + '\'' +
                ", repositoryPath='" + repositoryPath + '\'' +
                ", filePath='" + filePath + '\'' +
                ", startLine=" + startLine +
                ", endLine=" + endLine +
                ", currentHashCommit='" + currentHashCommit + '\'' +
                ", currentDate=" + currentDate +
                ", repositoryDevelopers=" + repositoryDevelopers +
                ", commits=" + commits +
                '}';
    }

}
