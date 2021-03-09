package input;

import domain.InvestigatedClass;
import domain.InvestigatedMethod;
import domain.InvestigatedSourceElement;
import mapper.InvestigatedClassMapper;
import mapper.InvestigatedMethodMapper;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class GetInvestigatedSourceElementsTask implements Supplier<List<InvestigatedSourceElement>> {

    private static int DONE_ELEMENTS = 0;
    private final List<CSVInputRow> rows;

    public GetInvestigatedSourceElementsTask(List<CSVInputRow> rows) {
        this.rows = rows;
    }

    @Override
    public List<InvestigatedSourceElement> get() {
        return rows.stream()
                .map(this::getInvestigatedSourceElement)
                .collect(Collectors.toList());
    }

    private InvestigatedSourceElement getInvestigatedSourceElement(CSVInputRow row) {
        DONE_ELEMENTS++;
        System.out.println(Thread.currentThread().getName() + " " + DONE_ELEMENTS);
        if (isClass(row.getType())) {
            return InvestigatedClassMapper.from(row);
        } else if (isMethodOrConstructor(row.getType())) {
            return InvestigatedMethodMapper.from(row);
        } else {
            throw new RuntimeException("Found unknown value " + row.getType() + " in column 'type'!");
        }
    }

    private static boolean isClass(String type) {
        return InvestigatedClass.CLASS_TYPE.equals(type);
    }

    private static boolean isMethodOrConstructor(String type) {
        return InvestigatedMethod.METHOD_TYPE.equals(type) || InvestigatedMethod.CONSTRUCTOR_TYPE.equals(type);
    }

}
