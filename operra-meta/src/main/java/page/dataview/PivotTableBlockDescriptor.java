package page.dataview;

import base.ContextualCondition;
import query.ResolvedQuery;

/**
 * بلاک جدول محوری برای نمایش داده‌ها به‌صورت ترکیبی از سطر/ستون‌های قابل دسته‌بندی
 */
public class PivotTableBlockDescriptor extends DataViewBlockDescriptor {

    private final ResolvedQuery<?> query;
    private final String rowField;
    private final String columnField;
    private final String valueField;

    private PivotTableBlockDescriptor(Builder builder) {
        super(builder.id, builder.title, builder.sectionId, builder.visibleWhen);
        this.query = builder.query;
        this.rowField = builder.rowField;
        this.columnField = builder.columnField;
        this.valueField = builder.valueField;
    }

    public ResolvedQuery<?> getQuery() { return query; }
    public String getRowField() { return rowField; }
    public String getColumnField() { return columnField; }
    public String getValueField() { return valueField; }

    public static Builder builder(String id, String title, ResolvedQuery<?> query) {
        return new Builder(id, title, query);
    }

    public static class Builder {
        private final String id;
        private final String title;
        private final ResolvedQuery<?> query;
        private String rowField;
        private String columnField;
        private String valueField;
        private String sectionId;
        private ContextualCondition visibleWhen = ContextualCondition.alwaysTrue();

        public Builder(String id, String title, ResolvedQuery<?> query) {
            this.id = id;
            this.title = title;
            this.query = query;
        }

        public Builder section(String sectionId) {
            this.sectionId = sectionId;
            return this;
        }

        public Builder rowField(String rowField) {
            this.rowField = rowField;
            return this;
        }

        public Builder columnField(String columnField) {
            this.columnField = columnField;
            return this;
        }

        public Builder valueField(String valueField) {
            this.valueField = valueField;
            return this;
        }

        public Builder visibleWhen(ContextualCondition condition) {
            this.visibleWhen = condition;
            return this;
        }

        public PivotTableBlockDescriptor build() {
            return new PivotTableBlockDescriptor(this);
        }
    }
}

