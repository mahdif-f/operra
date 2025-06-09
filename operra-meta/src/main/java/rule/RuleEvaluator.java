package rule;

/**
 * اینترفیس تابعی برای ارزیابی Rule به صورت قابل توسعه و تزریق پذیر.
 */
@FunctionalInterface
public interface RuleEvaluator<T> {
    RuleResult evaluate(T instance);
}
