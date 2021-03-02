package domain;

public class Metric<T extends Number> {

    private final String name;
    private final T value;

    public Metric(String name, T value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public T getValue() {
        return value;
    }

}
