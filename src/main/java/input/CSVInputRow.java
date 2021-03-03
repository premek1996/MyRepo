package input;

import java.util.List;

public class CSVInputRow {

    private final String type;
    private final String repositoryUri;
    private final String currentHashCommit;
    private final int startLine;
    private final int endLine;
    private final String filePath;
    private final String className;
    private final String methodName;
    private final List<String> parameters;

    private CSVInputRow(CSVInputRowBuilder builder) {
        this.type = builder.type;
        this.repositoryUri = builder.repositoryUri;
        this.currentHashCommit = builder.currentHashCommit;
        this.startLine = builder.startLine;
        this.endLine = builder.endLine;
        this.filePath = builder.filePath;
        this.className = builder.className;
        this.methodName = builder.methodName;
        this.parameters = builder.parameters;
    }

    public static CSVInputRowBuilder builder() {
        return new CSVInputRowBuilder();
    }

    public String getType() {
        return type;
    }

    public String getRepositoryUri() {
        return repositoryUri;
    }

    public String getCurrentHashCommit() {
        return currentHashCommit;
    }

    public int getStartLine() {
        return startLine;
    }

    public int getEndLine() {
        return endLine;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getClassName() {
        return className;
    }

    public String getMethodName() {
        return methodName;
    }

    public List<String> getParameters() {
        return parameters;
    }

    public static class CSVInputRowBuilder {

        private String type;
        private String repositoryUri;
        private String currentHashCommit;
        private int startLine;
        private int endLine;
        private String filePath;
        private String className;
        private String methodName;
        private List<String> parameters;

        public CSVInputRowBuilder withType(String type) {
            this.type = type;
            return this;
        }

        public CSVInputRowBuilder withRepositoryUri(String repositoryUri) {
            this.repositoryUri = repositoryUri;
            return this;
        }

        public CSVInputRowBuilder withCurrentHashCommit(String currentHashCommit) {
            this.currentHashCommit = currentHashCommit;
            return this;
        }

        public CSVInputRowBuilder withStartLine(int startLine) {
            this.startLine = startLine;
            return this;
        }

        public CSVInputRowBuilder withEndLine(int endLine) {
            this.endLine = endLine;
            return this;
        }

        public CSVInputRowBuilder withFilePath(String filePath) {
            this.filePath = filePath;
            return this;
        }

        public CSVInputRowBuilder withClassName(String className) {
            this.className = className;
            return this;
        }

        public CSVInputRowBuilder withMethodName(String methodName) {
            this.methodName = methodName;
            return this;
        }

        public CSVInputRowBuilder withParameters(List<String> parameters) {
            this.parameters = parameters;
            return this;
        }

        public CSVInputRow build() {
            return new CSVInputRow(this);
        }

    }

}
