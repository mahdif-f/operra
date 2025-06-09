package page.crud;

import ui.UiSectionDescriptor;

import java.util.ArrayList;
import java.util.List;

/**
 * توصیف کامل متادیتای یک فرم برای Entity از نوع T.
 * شامل فیلدهای اصلی، دیتیل‌ها، و سکشن‌های نمایشی.
 */
/**
 * توصیف محتوای اصلی یک فرم شامل فیلدهای ساده، فیلدهای پیچیده (مانند detail)، و سکشن‌ها
 */
public class PageContentDescriptor<T> {

    private final List<SimpleFieldDescriptor<T, ?>> fields;
    private final List<ComplexGalleryConfig<T, ?>> details;
    private final List<UiSectionDescriptor> sections;

    private PageContentDescriptor(Builder<T> builder) {
        this.fields = builder.fields;
        this.details = builder.details;
        this.sections = builder.sections;
    }

    public List<SimpleFieldDescriptor<T, ?>> getFields() {
        return fields;
    }

    public List<ComplexGalleryConfig<T, ?>> getDetails() {
        return details;
    }

    public List<UiSectionDescriptor> getSections() {
        return sections;
    }

    // --- Builder ---
    public static class Builder<T> {
        private final List<SimpleFieldDescriptor<T, ?>> fields = new ArrayList<>();
        private final List<ComplexGalleryConfig<T, ?>> details = new ArrayList<>();
        private final List<UiSectionDescriptor> sections = new ArrayList<>();

        public Builder<T> fields(List<SimpleFieldDescriptor<T, ?>> fields) {
            this.fields.addAll(fields);
            return this;
        }

        public Builder<T> addField(SimpleFieldDescriptor<T, ?> field) {
            this.fields.add(field);
            return this;
        }

        public Builder<T> details(List<ComplexGalleryConfig<T, ?>> details) {
            this.details.addAll(details);
            return this;
        }

        public Builder<T> addDetail(ComplexGalleryConfig<T, ?> detail) {
            this.details.add(detail);
            return this;
        }

        public Builder<T> sections(List<UiSectionDescriptor> sections) {
            this.sections.addAll(sections);
            return this;
        }

        public Builder<T> addSection(UiSectionDescriptor section) {
            this.sections.add(section);
            return this;
        }

        public Builder<T> addFieldInSection(String sectionId, SimpleFieldDescriptor<T, ?> field) {
//            field.setSectionId(sectionId);
            this.fields.add(field);
            return this;
        }

        public PageContentDescriptor<T> build() {
            return new PageContentDescriptor<>(this);
        }
    }
}
