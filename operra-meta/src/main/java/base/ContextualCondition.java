package base;

import java.util.*;
import java.util.Locale;

/**
 * اینترفیس عمومی برای تعریف شرایط وابسته به context جهت کنترل visibility، enable بودن، یا اجرا شدن عناصر فرم.
 * <p>
 * این شرط می‌تواند در بخش‌های مختلف سیستم اعمال شود، مثل:
 * - سکشن‌ها (برای نمایش یا پنهان‌سازی)
 * - فیلدها (برای readonly، required، visible)
 * - دکمه‌ها و اکشن‌ها (برای enable/disable)
 * - ruleها (برای کنترل شرط اجرا)
 * </p>
 *
 * استفاده از این اینترفیس باعث می‌شود تا منطق نمایش یا اجرا به صورت دکلریتیو، قابل تست و قابل ترکیب باقی بماند.
 */
@FunctionalInterface
public interface ContextualCondition {

    /**
     * بررسی اینکه آیا شرط در context فعلی برقرار است یا نه
     * @param context اطلاعات موجود در زمان ارزیابی
     * @return true اگر شرط برقرار باشد
     */
    boolean isSatisfied(ConditionContext context);

    static ContextualCondition alwaysTrue() {
        return ctx -> true;
    }

    static ContextualCondition alwaysFalse() {
        return ctx -> false;
    }

    /**
     * اطلاعات موجود هنگام ارزیابی شرط‌ها.
     * این اطلاعات معمولاً در اختیار فرم‌ساز یا rule engine قرار می‌گیرد.
     */
    interface ConditionContext {
        /** entity اصلی فرم */
        Object getEntity();

        /** شناسه context فعلی (مثلاً "customerForm") */
        String getContextId();

        /** نقش کاربر جاری، در صورت وجود */
        Optional<String> getCurrentUserRole();

        /** زبان یا locale جاری */
        Optional<Locale> getLocale();

        /** سایر اطلاعات دلخواه که ممکن است در session یا فرم موجود باشند */
        Map<String, Object> getAttributes();
    }



}
