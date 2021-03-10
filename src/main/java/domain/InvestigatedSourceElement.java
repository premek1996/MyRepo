package domain;

import gitapi.CommitBasicInfoApi;
import gitapi.DevelopersApi;
import processmetrics.ProcessMetricsCalculator;
import utils.TextComparator;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
    protected List<Metric> metrics;

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
        this.metrics = determineMetrics();
    }

    private LocalDate determineCurrentDate() {
        return CommitBasicInfoApi.getCommitBasicInfo(repositoryPath, currentHashCommit).getDate();
    }

    private List<Developer> determineRepositoryDevelopers() {
        return DevelopersApi.getDevelopers(repositoryPath);
    }

    protected abstract List<Commit> determineCommits();

    private List<Metric> determineMetrics() {
        return commits.isEmpty() ? Collections.emptyList() : ProcessMetricsCalculator.getMetrics(this);
    }

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

    public Developer getDeveloper(String mail) {
        return repositoryDevelopers.stream()
                .filter(developer -> developer.getMail().equals(mail))
                .findAny()
                .orElse(null);
    }

    public Developer getDeveloperWithMostSimilarMail(String mail) {
        List<String> developersMails = repositoryDevelopers.stream()
                .map(Developer::getMail)
                .collect(Collectors.toList());
        String mostSimilarMail = TextComparator.getMostSimilarText(mail, developersMails);
        return getDeveloper(mostSimilarMail);
    }

    public LocalDate getCurrentDate() {
        return currentDate;
    }

    public List<Commit> getCommits() {
        return commits;
    }

    public List<Metric> getMetrics() {
        return metrics;
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
                ", metrics=" + metrics +
                '}';
    }

}
