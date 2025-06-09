package rule;


/**
 * نتیجه ارزیابی یک Rule.
 */
public class RuleResult {

    public enum Severity {
        INFO, WARNING, ERROR
    }

    private final boolean passed;
    private final String messageKey;
    private final Severity severity;

    private RuleResult(boolean passed, String messageKey, Severity severity) {
        this.passed = passed;
        this.messageKey = messageKey;
        this.severity = severity;
    }

    public static RuleResult passed() {
        return new RuleResult(true, null, null);
    }

    public static RuleResult failed(String messageKey, Severity severity) {
        return new RuleResult(false, messageKey, severity);
    }

    public boolean isPassed() {
        return passed;
    }

    public String getMessageKey() {
        return messageKey;
    }

    public Severity getSeverity() {
        return severity;
    }
}