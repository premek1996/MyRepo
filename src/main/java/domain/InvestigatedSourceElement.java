package domain;

import gitapi.CommitBasicInfoApi;
import gitapi.DevelopersApi;

import java.util.Date;
import java.util.List;

public class InvestigatedSourceElement {

    private final String repoPath;
    private final List<Developer> repoDevelopers;
    private final String filePath;
    private final int startLine;
    private final int endLine;
    private final String currentHashCommit;
    private final Date currentDate;

    private InvestigatedSourceElement(String repoPath,
                                      String filePath,
                                      int startLine,
                                      int endLine,
                                      String currentHashCommit) {
        this.repoPath = repoPath;
        this.filePath = filePath;
        this.startLine = startLine;
        this.endLine = endLine;
        this.currentHashCommit = currentHashCommit;
        this.currentDate = determineCurrentDate();
        this.repoDevelopers = determineRepoDevelopers();
    }

    public static InvestigatedSourceElementBuilder builder() {
        return new InvestigatedSourceElementBuilder();
    }

    private Date determineCurrentDate() {
        return CommitBasicInfoApi.getCommitBasicInfo(repoPath, currentHashCommit).getDate();
    }

    private List<Developer> determineRepoDevelopers() {
        return DevelopersApi.getDevelopers(repoPath);
    }

    public String getRepoPath() {
        return repoPath;
    }

    public String getFilePath() {
        return filePath;
    }

    public List<Developer> getRepoDevelopers() {
        return repoDevelopers;
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
        return repoDevelopers.stream()
                .filter(developer -> developer.getMail().equals(mail))
                .findAny()
                .orElse(null);
    }

    public Date getCurrentDate() {
        return currentDate;
    }

    @Override
    public String toString() {
        return "InvestigatedSourceElement{" +
                "repoPath='" + repoPath + '\'' +
                ", repoDevelopers=" + repoDevelopers +
                ", filePath='" + filePath + '\'' +
                ", startLine=" + startLine +
                ", endLine=" + endLine +
                ", currentHashCommit='" + currentHashCommit + '\'' +
                ", currentDate=" + currentDate +
                '}';
    }

    public static class InvestigatedSourceElementBuilder {

        private String repoPath;
        private String filePath;
        private int startLine;
        private int endLine;
        private String currentHashCommit;

        public InvestigatedSourceElementBuilder withRepoPath(String repoPath) {
            this.repoPath = repoPath;
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
            return new InvestigatedSourceElement(repoPath, filePath, startLine,
                    endLine, currentHashCommit);
        }

    }

}
