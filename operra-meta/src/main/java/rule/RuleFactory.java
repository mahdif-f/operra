package rule;


import base.FieldNameResolver;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Factory کلاس برای ساخت Ruleها به صورت DSL و خوانا.
 * <p>
 * این کلاس تنها از {@link EvaluatedRule} استفاده می‌کند و امکان تعریف سریع Ruleهای ساده را فراهم می‌سازد.
 * برای Ruleهای پیچیده‌تر، می‌توان مستقیماً از {@link RuleEvaluator} استفاده کرد.
 * </p>
 *
 * @see Rule
 * @see EvaluatedRule
 */
public class RuleFactory {

    /**
     * شروع ساخت یک Rule ساده با استفاده از DSL.
     */
    public static <T> SimpleRuleBuilder<T> simple() {
        return new SimpleRuleBuilder<>();
    }

    /**
     * Builder داخلی برای ساخت Ruleهای ساده و ترکیب‌پذیر.
     *
     * @param <T> نوع entity که Rule روی آن اعمال می‌شود.
     */
    public static class SimpleRuleBuilder<T> {
        private RuleType type = RuleType.VALIDATION;
        private RuleTrigger trigger = RuleTrigger.ON_SUBMIT;
        private RuleSeverity severity = RuleSeverity.ERROR;
        private Set<String> affectedFields = new HashSet<>();
        private Set<FieldDependencyRef<T>> dependencies = new HashSet<>();
        private RuleEvaluator<T> evaluator;
        private String messageKey;

        /** تنظیم نوع Rule (Business, UI, etc) */
        public SimpleRuleBuilder<T> type(RuleType type) {
            this.type = type;
            return this;
        }

        /** تنظیم Trigger برای Rule (ON_SUBMIT, ON_CHANGE, etc) */
        public SimpleRuleBuilder<T> trigger(RuleTrigger trigger) {
            this.trigger = trigger;
            return this;
        }

        /** تنظیم شدت Rule به صورت مستقیم */
        public SimpleRuleBuilder<T> severity(RuleSeverity severity) {
            this.severity = severity;
            return this;
        }

        /** شدت خطا - حالت پیش‌فرض (ERROR) */
        public SimpleRuleBuilder<T> error() {
            return severity(RuleSeverity.ERROR);
        }

        /** شدت هشدار (WARNING) */
        public SimpleRuleBuilder<T> warning() {
            return severity(RuleSeverity.WARNING);
        }

        /** شدت اطلاع (INFO) */
        public SimpleRuleBuilder<T> info() {
            return severity(RuleSeverity.INFO);
        }

        /** لیست فیلدهایی که این Rule روی آن‌ها اثر دارد (با نام صریح) */
        public SimpleRuleBuilder<T> on(String... fields) {
            Collections.addAll(this.affectedFields, fields);
            return this;
        }

        /** لیست فیلدهایی که این Rule روی آن‌ها اثر دارد (با getterهای type-safe) */
        @SafeVarargs
        public final SimpleRuleBuilder<T> on(Function<T, ?>... fieldGetters) {
            for (Function<T, ?> getter : fieldGetters) {
                this.affectedFields.add(FieldNameResolver.resolve(getter));
            }
            return this;
        }

        /** وابستگی به فیلدهای دیگر برای اجرای Rule */
        @SafeVarargs
        public final SimpleRuleBuilder<T> dependsOn(FieldDependencyRef<T>... refs) {
            this.dependencies.addAll(Arrays.asList(refs));
            return this;
        }

        /** کلید پیام خطای Rule */
        public SimpleRuleBuilder<T> message(String messageKey) {
            this.messageKey = messageKey;
            return this;
        }

        /** شرط اجرا با استفاده از RuleEvaluator کامل */
        public SimpleRuleBuilder<T> when(RuleEvaluator<T> evaluator) {
            this.evaluator = evaluator;
            return this;
        }

        /** شرط اجرا با استفاده از Predicate ساده */
        public SimpleRuleBuilder<T> when(Predicate<T> predicate) {
            this.evaluator = instance -> predicate.test(instance)
                    ? RuleResult.passed()
                    : RuleResult.failed(messageKey, RuleResult.Severity.valueOf(severity.name()));
            return this;
        }

        /** ساخت Rule نهایی */
        public Rule<T> build() {
            return new EvaluatedRule<>(
                    type,
                    trigger,
                    severity,
                    affectedFields,
                    evaluator,
                    messageKey
            );
        }
    }
}
