package meta;


public class DetailDescriptor {
    private final String fieldName; // نام فیلد در کلاس Master
    private final String title;     // عنوان قابل نمایش
    private final Class<?> detailClass;

    public DetailDescriptor(String fieldName, String title, Class<?> detailClass) {
        this.fieldName = fieldName;
        this.title = title;
        this.detailClass = detailClass;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getTitle() {
        return title;
    }

    public Class<?> getDetailClass() {
        return detailClass;
    }
}
