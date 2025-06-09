package page.dataview;

import base.ContextualCondition;
import query.ResolvedQuery;

import java.util.Optional;

public class DataListBlockDescriptor extends DataViewBlockDescriptor {

    private final ResolvedQuery<?> query;
    private final Optional<String> navigateToCrudPageId;

    private DataListBlockDescriptor(Builder builder) {
        super(builder.id, builder.title, builder.sectionId, builder.visibleWhen);
        this.query = builder.query;
        this.navigateToCrudPageId = Optional.ofNullable(builder.navigateToCrudPageId);
    }

    public ResolvedQuery<?> getQuery() { return query; }
    public Optional<String> getNavigateToCrudPageId() { return navigateToCrudPageId; }

    public static Builder builder(String id, String title, ResolvedQuery<?> query) {
        return new Builder(id, title, query);
    }

    public static class Builder {
        private final String id;
        private final String title;
        private final ResolvedQuery<?> query;
        private String sectionId;
        private ContextualCondition visibleWhen = ContextualCondition.alwaysTrue();
        private String navigateToCrudPageId;

        public Builder(String id, String title, ResolvedQuery<?> query) {
            this.id = id;
            this.title = title;
            this.query = query;
        }

        public Builder section(String sectionId) {
            this.sectionId = sectionId;
            return this;
        }

        public Builder visibleWhen(ContextualCondition visibleWhen) {
            this.visibleWhen = visibleWhen;
            return this;
        }

        public Builder navigateToCrud(String pageId) {
            this.navigateToCrudPageId = pageId;
            return this;
        }

        public DataListBlockDescriptor build() {
            return new DataListBlockDescriptor(this);
        }
    }
}
