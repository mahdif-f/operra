package query;


/**
 * توصیف‌گر یک پارامتر مورد استفاده در {@link QueryDescriptor}‌ها.
 * این کلاس مشخص می‌کند که نام پارامتر و نوع آن چیست.
 */
public class QueryParamDescriptor {

    /**
     * نام پارامتر که معمولاً در کوئری به صورت :name استفاده می‌شود.
     */
    private final String name;
    private final String title;

    /**
     * نوع داده‌ای پارامتر (مثلاً String.class، Long.class و ...)
     */
    private final Class<?> type;


    public QueryParamDescriptor(String name, String title, Class<?> type) {
        this.name = name;
        this.title = title;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public Class<?> getType() {
        return type;
    }
}