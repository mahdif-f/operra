package page.crud;

import model.base.BaseEntity;
import ui.UiSectionDescriptor;

import java.util.List;

public class SimpleCrudPageDescriptor<T extends BaseEntity> extends CrudPageDescriptor<T> {

    private final PageContentDescriptor<T> content;

    private SimpleCrudPageDescriptor(Builder<T> builder) {
        super(builder);
        this.content = builder.contentBuilder.build();
    }

    public PageContentDescriptor<T> getContent() { return content; }

    public static <T extends BaseEntity> Builder<T> builder(Class<T> entityType) {
        return new Builder<>(entityType);
    }

    public static class Builder<T extends BaseEntity> extends CrudPageDescriptor.Builder<T, Builder<T>> {
        private final PageContentDescriptor.Builder<T> contentBuilder = new PageContentDescriptor.Builder<>();

        public Builder(Class<T> entityType) {
            super(entityType);
        }

        public Builder<T> addField(SimpleFieldDescriptor<T, ?> field) {
            this.contentBuilder.addField(field);
            return this;
        }

        public Builder<T> fields(List<SimpleFieldDescriptor<T, ?>> fields) {
            this.contentBuilder.fields(fields);
            return this;
        }

        public Builder<T> addDetail(ComplexFieldDescriptor<T, ?> detail) {
            this.contentBuilder.addDetail(detail);
            return this;
        }

        public Builder<T> details(List<ComplexFieldDescriptor<T, ?>> details) {
            this.contentBuilder.details(details);
            return this;
        }

        public Builder<T> sections(List<UiSectionDescriptor> sections) {
            this.contentBuilder.sections(sections);
            return this;
        }

        @Override
        protected Builder<T> self() {
            return this;
        }

        public SimpleCrudPageDescriptor<T> build() {
            return new SimpleCrudPageDescriptor<>(this);
        }
    }
}