package domain;

import java.util.List;

public class MethodSourceElement extends InvestigatedSourceElement {

    public static final String METHOD_TYPE = "method";
    public static final String CONSTRUCTOR_TYPE = "constructor";

    private final String className;
    private final String methodName;
    private final List<String> arguments;

    public MethodSourceElement(String repositoryPath,
                               String filePath,
                               int startLine,
                               int endLine,
                               String currentHashCommit,
                               String className,
                               String methodName,
                               List<String> arguments) {
        super(repositoryPath, filePath, startLine, endLine, currentHashCommit);
        this.className = className;
        this.methodName = methodName;
        this.arguments = arguments;
    }

}
