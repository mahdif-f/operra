package meta;


import java.util.function.Predicate;
import java.util.function.Consumer;

public class RuleDescriptor<T> {
    private final String name;
    private final RuleTrigger trigger;
    private final Predicate<T> condition;
    private final Consumer<T> action;

    public RuleDescriptor(String name, RuleTrigger trigger, Predicate<T> condition, Consumer<T> action) {
        this.name = name;
        this.trigger = trigger;
        this.condition = condition;
        this.action = action;
    }

    public String getName() {
        return name;
    }

    public RuleTrigger getTrigger() {
        return trigger;
    }

    public boolean evaluate(T entity) {
        return condition.test(entity);
    }

    public void execute(T entity) {
        action.accept(entity);
    }
}
