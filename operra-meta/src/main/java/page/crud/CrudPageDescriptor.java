package page.crud;

import base.ContextualCondition;
import model.base.BaseEntity;
import page.PageDescriptor;
import query.QueryDescriptor;
import rule.Rule;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * توصیف یک Context از نوع CRUD یا Wizard.
 * این کلاس شامل متادیتای فرم، ruleها و سایر تنظیمات است.
 *
 * @param <T> نوع entity اصلی
 */
public abstract class CrudPageDescriptor<T extends BaseEntity> extends PageDescriptor {

    private final Class<T> entityType;
    private final Supplier<T> defaultInstance;
    private final ContextualCondition visibilityRule;
    private final List<Rule<T>> rules;
    private final QueryDescriptor<T> historyData;

    protected CrudPageDescriptor(Builder<T, ?> builder) {
        this.entityType = builder.entityType;
        this.defaultInstance = builder.defaultInstance;
        this.visibilityRule = builder.visibilityRule;
        this.rules = builder.rules;
        this.historyData = builder.historyData;
    }

    public Class<T> getEntityType() {
        return entityType;
    }

    public Supplier<T> getDefaultInstance() {
        return defaultInstance;
    }

    public ContextualCondition getVisibilityRule() {
        return visibilityRule;
    }

    public List<Rule<T>> getRules() {
        return rules;
    }

    public QueryDescriptor<T> getHistoryData() {
        return historyData;
    }

    // ---- Builder مشترک ----
    public abstract static class Builder<T extends BaseEntity, SELF extends Builder<T, SELF>> {
        protected final Class<T> entityType;
        protected Supplier<T> defaultInstance;
        protected ContextualCondition visibilityRule = ContextualCondition.alwaysTrue();
        protected List<Rule<T>> rules = new ArrayList<>();
        protected QueryDescriptor<T> historyData;

        protected Builder(Class<T> entityType) {
            this.entityType = entityType;
        }

        public SELF visibilityRule(ContextualCondition condition) {
            this.visibilityRule = condition;
            return self();
        }

        public SELF defaultInstance(Supplier<T> defaultInstance) {
            this.defaultInstance = defaultInstance;
            return self();
        }

        public SELF addRule(Rule<T> rule) {
            this.rules.add(rule);
            return self();
        }

        public SELF rules(List<Rule<T>> rules) {
            this.rules.addAll(rules);
            return self();
        }

        public SELF historyData(QueryDescriptor<T> queryDescriptor) {
            this.historyData = queryDescriptor;
            return self();
        }

        protected abstract SELF self();
    }
}