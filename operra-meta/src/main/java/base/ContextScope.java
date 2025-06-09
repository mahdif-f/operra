package base;

import java.util.Map;

/**
 * محدودهٔ اجرایی برای resolve کردن مقادیر پارامترها، شامل فرم جاری، سشن کاربر،
 * ورودی‌های کاربر، و متغیرهای سیستمی.
 */
public class ContextScope {

    private final Object currentEntity;
    private final Map<String, Object> userInput;
    private final Map<String, Object> session;
    private final Map<String, Object> system;

    public ContextScope(Object currentEntity,
                        Map<String, Object> userInput,
                        Map<String, Object> session,
                        Map<String, Object> system) {
        this.currentEntity = currentEntity;
        this.userInput = userInput != null ? userInput : Map.of();
        this.session = session != null ? session : Map.of();
        this.system = system != null ? system : Map.of();
    }

    /**
     * فرم یا entity جاری (مثلاً سفارش جاری)
     */
    public Object getCurrentEntity() {
        return currentEntity;
    }

    /**
     * دریافت مقدار دلخواه از user input
     */
    public Object fromUserInput(String key) {
        return userInput.get(key);
    }

    /**
     * دریافت مقدار از سشن کاربر
     */
    public Object fromSession(String key) {
        return session.get(key);
    }

    /**
     * دریافت مقدار از منابع سیستمی (مثلاً now، locale، تنظیمات سازمانی و ...)
     */
    public Object fromSystem(String key) {
        return system.get(key);
    }
}
