package domain;

import java.util.List;

public class InvestigatedClass extends InvestigatedSourceElement {

    public static final String CLASS_TYPE = "class";

    private final String className;

    private InvestigatedClass(InvestigatedClassBuilder investigatedClassBuilder) {
        super(investigatedClassBuilder.repositoryPath,
                investigatedClassBuilder.filePath,
                investigatedClassBuilder.startLine,
                investigatedClassBuilder.endLine,
                investigatedClassBuilder.currentHashCommit);
        this.className = investigatedClassBuilder.className;
    }

    public static InvestigatedClassBuilder builder() {
        return new InvestigatedClassBuilder();
    }

    public String getClassName() {
        return className;
    }

    @Override
    public String toString() {
        return "InvestigatedClass{" +
                "className='" + className + '\'' +
                ", repositoryPath='" + repositoryPath + '\'' +
                ", repositoryDevelopers=" + repositoryDevelopers +
                ", filePath='" + filePath + '\'' +
                ", startLine=" + startLine +
                ", endLine=" + endLine +
                ", currentHashCommit='" + currentHashCommit + '\'' +
                ", currentDate=" + currentDate +
                ", commits=" + commits +
                '}';
    }

    @Override
    protected List<Commit> determineCommits() {
        return null;
    }

    public static class InvestigatedClassBuilder {

        private String repositoryPath;
        private String filePath;
        private int startLine;
        private int endLine;
        private String currentHashCommit;
        private String className;

        public InvestigatedClassBuilder withRepositoryPath(String repositoryPath) {
            this.repositoryPath = repositoryPath;
            return this;
        }

        public InvestigatedClassBuilder withFilePath(String filePath) {
            this.filePath = filePath;
            return this;
        }

        public InvestigatedClassBuilder withStartLine(int startLine) {
            this.startLine = startLine;
            return this;
        }

        public InvestigatedClassBuilder withEndLine(int endLine) {
            this.endLine = endLine;
            return this;
        }

        public InvestigatedClassBuilder withCurrentHashCommit(String currentHashCommit) {
            this.currentHashCommit = currentHashCommit;
            return this;
        }

        public InvestigatedClassBuilder withClassName(String className) {
            this.className = className;
            return this;
        }

        public InvestigatedClass build() {
            return new InvestigatedClass(this);
        }

    }

}
