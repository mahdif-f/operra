package page.crud;

/**
 * توصیف‌گر ساختار هر آیتم گالری برای نمایش در UI.
 * این متادیتا به فرانت‌اند می‌گوید که از کدام فیلدها در ردیف داده‌ها برای نمایش
 * تصویر، عنوان، توضیح و آیدی استفاده کند.
 *
 * این کلاس فقط جنبه‌ی نمایشی دارد و به منبع دیتا (مثل کوئری) وابسته نیست.
 */
public class GalleryItemDescriptor {

    private final String idField;
    private final String titleField;
    private final String imageField;
    private final String subtitleField;

    public GalleryItemDescriptor(String idField, String titleField, String imageField, String subtitleField) {
        this.idField = idField;
        this.titleField = titleField;
        this.imageField = imageField;
        this.subtitleField = subtitleField;
    }

    public String getIdField() { return idField; }
    public String getTitleField() { return titleField; }
    public String getImageField() { return imageField; }
    public String getSubtitleField() { return subtitleField; }

    // --- DSL ---
    public static GalleryItemDescriptor of(String idField, String titleField) {
        return new GalleryItemDescriptor(idField, titleField, null, null);
    }

    public static GalleryItemDescriptor withImage(String idField, String titleField, String imageField) {
        return new GalleryItemDescriptor(idField, titleField, imageField, null);
    }

    public static GalleryItemDescriptor full(String idField, String titleField, String imageField, String subtitleField) {
        return new GalleryItemDescriptor(idField, titleField, imageField, subtitleField);
    }
}
