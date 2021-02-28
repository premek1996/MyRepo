package domain;

public class ClassSourceElement extends InvestigatedSourceElement {

    public static final String CLASS_TYPE = "class";

    private final String className;

    public ClassSourceElement(String repositoryPath,
                              String filePath,
                              int startLine,
                              int endLine,
                              String currentHashCommit,
                              String className) {
        super(repositoryPath, filePath, startLine, endLine, currentHashCommit);
        this.className = className;
    }

}
