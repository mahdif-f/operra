package page.crud;

import model.base.BaseEntity;
import query.ResolvedQuery;

import java.util.Map;
import java.util.function.Function;

/**
 * پیکربندی یک بخش دیتیل در فرم‌های Master-Detail.
 * این کلاس می‌تواند یک دیتیل ساده باشد یا از طریق انتخاب آیتم (مثلاً گالری) پر شود.
 *
 * @param <T> نوع entity اصلی (مثل Order)
 * @param <D> نوع entity دیتیل (مثل OrderItem)
 */
/**
 * پیکربندی آیتم‌های detail که در فرم‌های master-detail استفاده می‌شود.
 * می‌تواند به صورت ساده یا با انتخاب از گالری باشد.
 */
/**
 * توصیف تنظیمات انتخاب آیتم برای فیلدهای Complex که حالت Gallery دارند.
 * این تنظیمات زمانی کاربرد دارد که کاربر باید از میان لیستی از آیتم‌ها (مثلاً کالاها)،
 * انتخاب کند و آیتم انتخابی به یک رکورد detail تبدیل شود.
 *
 * این کلاس فقط زمانی استفاده می‌شود که فیلد Complex در حالت collection (لیستی) باشد.
 *
 * @param <T> نوع entity والد
 * @param <D> نوع entity آیتم‌های detail
 */
public class ComplexGalleryConfig<T, D extends BaseEntity> {

    private final ResolvedQuery<?> itemSelectionQuery;
    private final Function<Map<String, Object>, D> itemToDetailMapper;
    private final GalleryItemDescriptor galleryItemDescriptor;

    public ComplexGalleryConfig(ResolvedQuery<?> itemSelectionQuery,
                                Function<Map<String, Object>, D> itemToDetailMapper,
                                GalleryItemDescriptor galleryItemDescriptor) {
        this.itemSelectionQuery = itemSelectionQuery;
        this.itemToDetailMapper = itemToDetailMapper;
        this.galleryItemDescriptor = galleryItemDescriptor;
    }

    public ResolvedQuery<?> getItemSelectionQuery() {
        return itemSelectionQuery;
    }

    public Function<Map<String, Object>, D> getItemToDetailMapper() {
        return itemToDetailMapper;
    }

    public GalleryItemDescriptor getGalleryItemDescriptor() {
        return galleryItemDescriptor;
    }

    // --- DSL ---
    public static <T, D extends BaseEntity> ComplexGalleryConfig<T, D> of(
            ResolvedQuery<?> query,
            Function<Map<String, Object>, D> mapper,
            GalleryItemDescriptor descriptor
    ) {
        return new ComplexGalleryConfig<>(query, mapper, descriptor);
    }
}
