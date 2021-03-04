package domain;

import gitapi.MethodModificationsApi;

import java.util.List;

public class InvestigatedMethod extends InvestigatedSourceElement {

    public static final String METHOD_TYPE = "method";
    public static final String CONSTRUCTOR_TYPE = "constructor";
    private final String className;
    private final String methodName;
    private final List<String> arguments;

    private InvestigatedMethod(InvestigatedMethodBuilder investigatedMethodBuilder) {
        super(investigatedMethodBuilder.repositoryUri,
                investigatedMethodBuilder.repositoryPath,
                investigatedMethodBuilder.filePath,
                investigatedMethodBuilder.startLine,
                investigatedMethodBuilder.endLine,
                investigatedMethodBuilder.currentHashCommit);
        this.className = investigatedMethodBuilder.className;
        this.methodName = investigatedMethodBuilder.methodName;
        this.arguments = investigatedMethodBuilder.arguments;
    }

    public static InvestigatedMethodBuilder builder() {
        return new InvestigatedMethodBuilder();
    }

    @Override
    public String getClassName() {
        return className;
    }

    @Override
    public String getMethodName() {
        return methodName;
    }

    public List<String> getArguments() {
        return arguments;
    }

    @Override
    public String toString() {
        return "InvestigatedMethod{" +
                "className='" + className + '\'' +
                ", methodName='" + methodName + '\'' +
                ", arguments=" + arguments +
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
        return MethodModificationsApi.getCommits(this);
    }

    public static class InvestigatedMethodBuilder {

        private String repositoryUri;
        private String repositoryPath;
        private String filePath;
        private int startLine;
        private int endLine;
        private String currentHashCommit;
        private String className;
        private String methodName;
        private List<String> arguments;

        public InvestigatedMethodBuilder withRepositoryUri(String repositoryUri) {
            this.repositoryUri = repositoryUri;
            return this;
        }

        public InvestigatedMethodBuilder withRepositoryPath(String repositoryPath) {
            this.repositoryPath = repositoryPath;
            return this;
        }

        public InvestigatedMethodBuilder withFilePath(String filePath) {
            this.filePath = filePath;
            return this;
        }

        public InvestigatedMethodBuilder withStartLine(int startLine) {
            this.startLine = startLine;
            return this;
        }

        public InvestigatedMethodBuilder withEndLine(int endLine) {
            this.endLine = endLine;
            return this;
        }

        public InvestigatedMethodBuilder withCurrentHashCommit(String currentHashCommit) {
            this.currentHashCommit = currentHashCommit;
            return this;
        }

        public InvestigatedMethodBuilder withClassName(String className) {
            this.className = className;
            return this;
        }

        public InvestigatedMethodBuilder withMethodName(String methodName) {
            this.methodName = methodName;
            return this;
        }

        public InvestigatedMethodBuilder withArguments(List<String> arguments) {
            this.arguments = arguments;
            return this;
        }

        public InvestigatedMethod build() {
            return new InvestigatedMethod(this);
        }

    }

}
