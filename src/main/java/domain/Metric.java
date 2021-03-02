package domain;

public class Metric {

    private final String name;
    private final Number value;

    public Metric(String name, Number value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Number getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Metric{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }

}
