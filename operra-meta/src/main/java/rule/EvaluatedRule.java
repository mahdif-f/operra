package rule;


import java.util.Set;

/**
 * Rule مبتنی بر RuleEvaluator قابل توسعه و پیشرفته.
 */
public class EvaluatedRule<T> implements Rule<T> {

    private final RuleType type;
    private final RuleTrigger trigger;
    private final RuleSeverity severity;
    private final Set<String> affectedFields;
    private final RuleEvaluator<T> evaluator;
    private final String messageKey;

    public EvaluatedRule(RuleType type,
                         RuleTrigger trigger,
                         RuleSeverity severity,
                         Set<String> affectedFields,
                         RuleEvaluator<T> evaluator,
                         String messageKey) {
        this.type = type;
        this.trigger = trigger;
        this.severity = severity;
        this.affectedFields = affectedFields;
        this.evaluator = evaluator;
        this.messageKey = messageKey;
    }

    @Override public RuleType getType() { return type; }
    @Override public RuleTrigger getTrigger() { return trigger; }
    @Override public RuleSeverity getSeverity() { return severity; }
    @Override public Set<String> getAffectedFields() { return affectedFields; }
    @Override public String getMessage() { return messageKey; }
    @Override public boolean evaluate(T instance) { return evaluator.evaluate(instance).isPassed(); }
}