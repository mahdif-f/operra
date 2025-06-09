package page.dataview;

import base.ContextualCondition;

/**
 * کلاس پایه برای بلاک‌هایی که می‌توانند در یک DataViewPage قرار بگیرند
 * مانند لیست، نمودار، جدول محوری و ...
 */
public abstract class DataViewBlockDescriptor {

    protected final String id;
    protected final String title;
    protected final String sectionId;
    protected final ContextualCondition visibleWhen;

    protected DataViewBlockDescriptor(String id, String title, String sectionId, ContextualCondition visibleWhen) {
        this.id = id;
        this.title = title;
        this.sectionId = sectionId;
        this.visibleWhen = visibleWhen;
    }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getSectionId() { return sectionId; }
    public ContextualCondition getVisibleWhen() { return visibleWhen; }
}
