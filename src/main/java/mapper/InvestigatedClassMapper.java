package mapper;

import domain.InvestigatedClass;
import input.CSVInputRow;

public class InvestigatedClassMapper implements InvestigatedSourceElementMapper {

    @Override
    public InvestigatedClass from(CSVInputRow row) {
        return InvestigatedClass.builder()
                .withRepositoryURI(row.getRepositoryURI())
                .withClassName(row.getClassName())
                .withCurrentHashCommit(row.getCurrentHashCommit())
                .withRepositoryPath(row.getRepositoryPath())
                .withStartLine(row.getStartLine())
                .withEndLine(row.getEndLine())
                .withFilePath(row.getFilePath())
                .build();
    }

}
