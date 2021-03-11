package mapper;

import domain.InvestigatedMethod;
import input.CSVInputRow;

public class InvestigatedMethodMapper implements InvestigatedSourceElementMapper {

    @Override
    public InvestigatedMethod from(CSVInputRow row) {
        return InvestigatedMethod.builder()
                .withRepositoryURI(row.getRepositoryURI())
                .withClassName(row.getClassName())
                .withCurrentHashCommit(row.getCurrentHashCommit())
                .withRepositoryPath(row.getRepositoryPath())
                .withStartLine(row.getStartLine())
                .withEndLine(row.getEndLine())
                .withFilePath(row.getFilePath())
                .withMethodName(row.getMethodName())
                .withArguments(row.getParameters())
                .build();
    }

}
