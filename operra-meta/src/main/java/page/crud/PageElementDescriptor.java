package page.crud;

import java.util.function.Function;

public abstract class PageElementDescriptor<T> {

    private final String titleKey;
    private final Class<?> valueType;
    private final Function<T, ?> valueAccessor;
    private final String sectionId;

    protected PageElementDescriptor(String titleKey, Class<?> valueType, Function<T, ?> valueAccessor, String sectionId) {
        this.titleKey = titleKey;
        this.valueType = valueType;
        this.valueAccessor = valueAccessor;
        this.sectionId = sectionId;
    }

    public String getTitleKey() {
        return titleKey;
    }

    public Class<?> getValueType() {
        return valueType;
    }

    public Function<T, ?> getValueAccessor() {
        return valueAccessor;
    }

    public String getSectionId() {
        return sectionId;
    }
}
