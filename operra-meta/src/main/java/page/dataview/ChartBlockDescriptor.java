package page.dataview;

import base.ContextualCondition;
import query.ResolvedQuery;

/**
 * بلاک نموداری برای نمایش اطلاعات به صورت گرافیکی (Bar, Pie, Line و ...)
 */
public class ChartBlockDescriptor extends DataViewBlockDescriptor {

    public enum ChartType { BAR, PIE, LINE, AREA }

    private final ResolvedQuery<?> query;
    private final ChartType chartType;

    private ChartBlockDescriptor(Builder builder) {
        super(builder.id, builder.title, builder.sectionId, builder.visibleWhen);
        this.query = builder.query;
        this.chartType = builder.chartType;
    }

    public ResolvedQuery<?> getQuery() { return query; }
    public ChartType getChartType() { return chartType; }

    public static Builder builder(String id, String title, ResolvedQuery<?> query, ChartType chartType) {
        return new Builder(id, title, query, chartType);
    }

    public static class Builder {
        private final String id;
        private final String title;
        private final ResolvedQuery<?> query;
        private final ChartType chartType;
        private String sectionId;
        private ContextualCondition visibleWhen = ContextualCondition.alwaysTrue();

        public Builder(String id, String title, ResolvedQuery<?> query, ChartType chartType) {
            this.id = id;
            this.title = title;
            this.query = query;
            this.chartType = chartType;
        }

        public Builder section(String sectionId) {
            this.sectionId = sectionId;
            return this;
        }

        public Builder visibleWhen(ContextualCondition visibleWhen) {
            this.visibleWhen = visibleWhen;
            return this;
        }

        public ChartBlockDescriptor build() {
            return new ChartBlockDescriptor(this);
        }
    }
}

