package backend;


import meta.FieldDescriptor;
import meta.FieldType;

public class FormField<T> {

    private final FieldDescriptor metaField;  // توصیف متا
    private T value;

    public FormField(FieldDescriptor metaField) {
        this.metaField = metaField;
    }

    public String getName() {
        return metaField.getName();
    }

    public FieldType getType() {
        return  metaField.getType();
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
