package mapper;

import domain.InvestigatedSourceElement;
import input.CSVInputRow;

public interface InvestigatedSourceElementMapper {

    InvestigatedSourceElement from(CSVInputRow row);

}
