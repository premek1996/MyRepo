package input;

import domain.InvestigatedSourceElement;
import domain.InvestigatedSourceElementType;
import mapper.InvestigatedClassMapper;
import mapper.InvestigatedMethodMapper;
import mapper.InvestigatedSourceElementMapper;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class GetInvestigatedSourceElementsTask implements Supplier<List<InvestigatedSourceElement>> {

    private static int DONE_ELEMENTS = 0;
    private final List<CSVInputRow> rows;
    private static final Map<InvestigatedSourceElementType, InvestigatedSourceElementMapper> investigatedSourceElementMappers
            = new EnumMap<>(InvestigatedSourceElementType.class);

    static {
        investigatedSourceElementMappers.put(InvestigatedSourceElementType.CLASS, new InvestigatedClassMapper());
        investigatedSourceElementMappers.put(InvestigatedSourceElementType.CONSTRUCTOR, new InvestigatedMethodMapper());
        investigatedSourceElementMappers.put(InvestigatedSourceElementType.METHOD, new InvestigatedMethodMapper());
    }

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
        InvestigatedSourceElementMapper investigatedSourceElementMapper = getInvestigatedSourceElementMapper(row.getType());
        return investigatedSourceElementMapper.from(row);
    }

    private InvestigatedSourceElementMapper getInvestigatedSourceElementMapper(String type) {
        return investigatedSourceElementMappers.get(getInvestigatedSourceElementType(type));
    }

    private InvestigatedSourceElementType getInvestigatedSourceElementType(String type) {
        return InvestigatedSourceElementType.valueOf(type.toUpperCase());
    }

}
