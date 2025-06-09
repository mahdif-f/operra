package page.crud;

import base.ContextualCondition;
import ui.UiSectionDescriptor;

import java.util.List;

public class WizardStepDescriptor<T> {

    private final String id;
    private final String title;
    private final PageContentDescriptor<T> content;
    private final ContextualCondition canGoNext;
    private final ContextualCondition canGoBack;

    public WizardStepDescriptor(String id, String title,
                                PageContentDescriptor<T> content,
                                ContextualCondition canGoNext,
                                ContextualCondition canGoBack) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.canGoNext = canGoNext != null ? canGoNext : ContextualCondition.alwaysTrue();
        this.canGoBack = canGoBack != null ? canGoBack : ContextualCondition.alwaysTrue();
    }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public PageContentDescriptor<T> getContent() { return content; }
    public ContextualCondition getCanGoNext() { return canGoNext; }
    public ContextualCondition getCanGoBack() { return canGoBack; }


    // ---- Builder داخلی DSL-friendly ----
    public static class Builder<T> {
        private final String id;
        private final String title;
        private PageContentDescriptor.Builder<T> contentBuilder = new PageContentDescriptor.Builder<>();
        private ContextualCondition canGoNext = ContextualCondition.alwaysTrue();
        private ContextualCondition canGoBack = ContextualCondition.alwaysTrue();

        public Builder(String id, String title) {
            this.id = id;
            this.title = title;
        }

        public Builder<T> content(PageContentDescriptor.Builder<T> contentBuilder) {
            this.contentBuilder = contentBuilder;
            return this;
        }

        public Builder<T> addField(SimpleFieldDescriptor<T, ?> field) {
            this.contentBuilder.addField(field);
            return this;
        }

        public Builder<T> addDetail(ComplexFieldDescriptor<T, ?> detail) {
            this.contentBuilder.addDetail(detail);
            return this;
        }

        public Builder<T> sections(List<UiSectionDescriptor> sections) {
            this.contentBuilder.sections(sections);
            return this;
        }

        public Builder<T> canGoNext(ContextualCondition condition) {
            this.canGoNext = condition;
            return this;
        }

        public Builder<T> canGoBack(ContextualCondition condition) {
            this.canGoBack = condition;
            return this;
        }

        public WizardStepDescriptor<T> build() {
            return new WizardStepDescriptor<>(
                    id,
                    title,
                    contentBuilder.build(),
                    canGoNext,
                    canGoBack
            );
        }
    }
}
