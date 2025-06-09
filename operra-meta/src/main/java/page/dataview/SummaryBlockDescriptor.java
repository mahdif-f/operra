package page.dataview;

import base.ContextualCondition;
import query.ResolvedQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * بلاک خلاصه‌ای از آمار عددی (مانند مجموع فروش، تعداد سفارش، میانگین قیمت و ...)
 */
public class SummaryBlockDescriptor extends DataViewBlockDescriptor {

    private final ResolvedQuery<?> query;
    private final List<String> summaryFields;

    private SummaryBlockDescriptor(Builder builder) {
        super(builder.id, builder.title, builder.sectionId, builder.visibleWhen);
        this.query = builder.query;
        this.summaryFields = builder.summaryFields;
    }

    public ResolvedQuery<?> getQuery() { return query; }
    public List<String> getSummaryFields() { return summaryFields; }

    public static Builder builder(String id, String title, ResolvedQuery<?> query) {
        return new Builder(id, title, query);
    }

    public static class Builder {
        private final String id;
        private final String title;
        private final ResolvedQuery<?> query;
        private List<String> summaryFields = new ArrayList<>();
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

        public Builder visibleWhen(ContextualCondition condition) {
            this.visibleWhen = condition;
            return this;
        }

        public Builder summaryFields(List<String> fields) {
            this.summaryFields = fields;
            return this;
        }

        public SummaryBlockDescriptor build() {
            return new SummaryBlockDescriptor(this);
        }
    }
}
