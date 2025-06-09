package page;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

/**
 * توصیف نمایش فیلد رفرنسی برای نمایش به کاربر، مانند "کد - نام".
 * این کلاس به صورت type-safe و ترکیبی طراحی شده و از استفاده مستقیم از String جلوگیری می‌کند.
 *
 * @param <V> نوع موجودیت رفرنسی (مثلاً ProductCategory)
 */
public class ReferenceFieldDisplay<V> {

    private final List<Function<V, ?>> parts = new ArrayList<>();

    /**
     * ایجاد یک ReferenceDisplay از یک یا چند تابع.
     * هر تابع بخشی از متن نهایی را تولید می‌کند.
     *
     * @param parts لیست توابعی که روی V اجرا می‌شوند و خروجی متنی دارند
     * @return یک ReferenceDisplay قابل استفاده
     */
    @SafeVarargs
    public static <V> ReferenceFieldDisplay<V> of(Function<V, ?>... parts) {
        ReferenceFieldDisplay<V> display = new ReferenceFieldDisplay<>();
        display.parts.addAll(Arrays.asList(parts));
        return display;
    }

    public List<Function<V, ?>> getParts() {
        return parts;
    }
}
