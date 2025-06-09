package rule;


import base.FieldNameResolver;

import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * اینترفیس پایه برای Ruleهای اعتبارسنجی و منطقی در فرم‌ها.
 */
public interface Rule<T> {
    RuleType getType();
    RuleTrigger getTrigger();
    RuleSeverity getSeverity();
    Set<String> getAffectedFields();
    String getMessage();
    boolean evaluate(T instance);

    static <T> String field(Function<T, ?> getter) {
        return FieldNameResolver.resolve(getter);
    }

    static <T> Set<String> fields(Function<T, ?>... getters) {
        return Stream.of(getters)
                .map(FieldNameResolver::resolve)
                .collect(Collectors.toSet());
    }
}