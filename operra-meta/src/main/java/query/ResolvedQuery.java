package query;

import base.ContextScope;

import java.util.*;
import java.util.function.Function;

/**
 * ساختار ترکیبی شامل:
 * - توصیف یک کوئری قابل اجرا
 * - نگاشت پارامترهای کوئری به مقادیر دینامیک از یک ContextScope
 * - اطلاعات مربوط به تفسیر خروجی (مانند valueField برای استخراج شناسه، و loader برای بازیابی entity اصلی)
 *
 * @param <T> نوع سطر خروجی کوئری (مثلاً Map<String, Object> یا یک DTO یا موجودیت)
 */
public record ResolvedQuery<T>(
        QueryDescriptor<T> query,

        /**
         * نگاشت نام پارامتر به تابع تأمین مقدار آن از ContextScope.
         */
        Map<String, Function<ContextScope, Object>> paramBindings,

        /**
         * نام فیلدی که مقدار شناسه (ID) از آن استخراج می‌شود.
         * در خروجی‌های columnar معمولاً برابر با "id".
         */
        String valueField,

        /**
         * نوع کلاس entity یا DTO واقعی که نتیجه انتخاب به آن تعلق دارد.
         * مثلاً Product.class برای lookup کالا.
         */
        Class<?> targetType,

        /**
         * تابعی برای بازیابی entity اصلی از روی ID. معمولاً با JPA یا Service.
         */
        Function<Object, ?> entityLoader
) {

    /**
     * استخراج مقدار پارامترهای کوئری بر اساس scope جاری.
     */
    public Map<String, Object> resolveParams(ContextScope scope) {
        Map<String, Object> result = new HashMap<>();
        for (var entry : paramBindings.entrySet()) {
            result.put(entry.getKey(), entry.getValue().apply(scope));
        }
        return result;
    }

    /**
     * استخراج مقدار ID از یک ردیف columnar.
     * @param row ردیف نتیجه از نوع Map<String, Object>
     * @return مقدار ID یا null
     */
    public Object extractIdFrom(Map<String, Object> row) {
        return row.get(valueField);
    }

    /**
     * بازیابی entity اصلی از یک ردیف columnar با استفاده از entityLoader.
     * @param row ردیف انتخاب‌شده
     * @return entity بازیابی‌شده، یا null
     */
    public Object loadEntityFromRow(Map<String, Object> row) {
        Object id = extractIdFrom(row);
        return entityLoader != null && id != null ? entityLoader.apply(id) : null;
    }
}
