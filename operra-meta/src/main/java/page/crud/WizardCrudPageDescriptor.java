package page.crud;

import model.base.BaseEntity;

import java.util.ArrayList;
import java.util.List;

public class WizardCrudPageDescriptor<T extends BaseEntity> extends CrudPageDescriptor<T> {

    private final List<WizardStepDescriptor<T>> steps;

    private WizardCrudPageDescriptor(Builder<T> builder) {
        super(builder);
        this.steps = builder.steps;
    }

    public List<WizardStepDescriptor<T>> getSteps() {
        return steps;
    }

    public static <T extends BaseEntity> Builder<T> builder(Class<T> entityType) {
        return new Builder<>(entityType);
    }

    public static class Builder<T extends BaseEntity> extends CrudPageDescriptor.Builder<T, Builder<T>> {
        private final List<WizardStepDescriptor<T>> steps = new ArrayList<>();

        public Builder(Class<T> entityType) {
            super(entityType);
        }

        public Builder<T> addStep(WizardStepDescriptor<T> step) {
            this.steps.add(step);
            return this;
        }

        public Builder<T> steps(List<WizardStepDescriptor<T>> steps) {
            this.steps.addAll(steps);
            return this;
        }

        @Override
        protected Builder<T> self() {
            return this;
        }

        public WizardCrudPageDescriptor<T> build() {
            return new WizardCrudPageDescriptor<>(this);
        }
    }
}