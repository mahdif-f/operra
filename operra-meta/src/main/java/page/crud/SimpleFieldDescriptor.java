package page.crud;

import java.util.function.*;

public class SimpleFieldDescriptor<T, V> extends PageElementDescriptor<T> {

    private final String fieldName;

    private SimpleFieldDescriptor(String titleKey, String fieldName, Class<V> valueType, Function<T, V> getter,String sectionId) {
        super(titleKey, valueType, getter,sectionId);
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }

    /** DSL برای ایجاد فیلد ساده */
    public static <T, V> SimpleFieldDescriptor<T, V> of(String titleKey, String fieldName, Class<V> valueType, Function<T, V> getter,String sectionId) {
        return new SimpleFieldDescriptor<>(titleKey, fieldName, valueType, getter,sectionId);
    }
}
