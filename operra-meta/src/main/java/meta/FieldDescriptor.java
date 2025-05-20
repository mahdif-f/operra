package meta;

public class FieldDescriptor {
    private final String name;
    private final String label;
    private final FieldType type;

    public FieldDescriptor(String name, String label, FieldType type) {
        this.name = name;
        this.label = label;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getLabel() {
        return label;
    }

    public FieldType getType() {
        return type;
    }
}
