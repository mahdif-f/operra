package query;



/**
 * توصیف‌گر یک ستون از نتایج بازگشتی از اجرای {@link QueryDescriptor}.
 * این کلاس مشخص می‌کند که یک فیلد در نتیجهٔ query چه نام، عنوان نمایشی و نوعی دارد.
 * از این توصیف برای تولید ستون‌های جدول، export، یا تفسیر نتایج query در UI استفاده می‌شود.
 */
public class QueryColumnDescriptor {

    /**
     * نام فیلد در نتایج query که باید با alias یا نام فیلد بازگشتی هماهنگ باشد.
     * معمولاً با نام ستونی در کوئری SQL یا JPQL یکسان است.
     */
    private final String field;

    /**
     * عنوان نمایشی فیلد که برای نمایش در UI (مثلاً عنوان ستون در جدول) استفاده می‌شود.
     */
    private final String title;

    /**
     * نوع داده‌ای فیلد، مانند String.class، Integer.class، Date.class و ...
     */
    private final Class<?> type;

    public QueryColumnDescriptor(String field, String title, Class<?> type) {
        this.field = field;
        this.title = title;
        this.type = type;
    }

    public String getField() {
        return field;
    }

    public String getTitle() {
        return title;
    }

    public Class<?> getType() {
        return type;
    }

}