package page;


/**
 * کلاس پایه برای توصیف انواع page‌هایی که در سیستم استفاده می‌شوند
 */
public class PageDescriptor {
    protected String pageId;
    protected String title;
    protected String helpText;

    public String getPageId() {
        return pageId;
    }

    public String getTitle() {
        return title;
    }

    public String getHelpText() {
        return helpText;
    }

}