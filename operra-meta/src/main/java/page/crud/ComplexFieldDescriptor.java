package page.crud;


import model.base.BaseEntity;

import java.util.Optional;
import java.util.function.Function;

public class ComplexFieldDescriptor<T, D extends BaseEntity> extends PageElementDescriptor<T> {

    private final boolean collection;
    private final PageContentDescriptor<D> content;
    private final ComplexGalleryConfig<T, D> galleryConfig;

    public ComplexFieldDescriptor(String titleKey,
                                  Class<D> type,
                                  Function<T, ?> valueGetter,
                                  boolean collection,
                                  PageContentDescriptor<D> content,
                                  ComplexGalleryConfig<T, D> galleryConfig,String sectionId) {
        super( titleKey, type, valueGetter,sectionId);
        this.collection = collection;
        this.content = content;
        this.galleryConfig = galleryConfig;
    }

    public boolean isCollection() {
        return collection;
    }

    public PageContentDescriptor<D> getContent() {
        return content;
    }

    public Optional<ComplexGalleryConfig<T, D>> getGalleryConfig() {
        return Optional.ofNullable(galleryConfig);
    }

    // --- DSL ---
    public static <T,  D extends BaseEntity> Builder<T, D> builder(String titleKey, Class<D> type,
                                               Function<T, ?> getter) {
        return new Builder<>(titleKey, type, getter);
    }

    public static class Builder<T, D extends BaseEntity> {
        private final String titleKey;
        private final Class<D> type;
        private final Function<T, ?> getter;

        private boolean collection = false;
        private PageContentDescriptor<D> content;
        private ComplexGalleryConfig<T, D> galleryConfig;
        private String sectionId;

        public Builder(String titleKey, Class<D> type, Function<T, ?> getter) {
            this.titleKey = titleKey;
            this.type = type;
            this.getter = getter;
        }

        public Builder<T, D> asCollection() {
            this.collection = true;
            return this;
        }

        public Builder<T, D> content(PageContentDescriptor<D> content) {
            this.content = content;
            return this;
        }

        public Builder<T, D> galleryConfig(ComplexGalleryConfig<T, D> config) {
            this.galleryConfig = config;
            return this;
        }

        public ComplexFieldDescriptor<T, D> build() {
            return new ComplexFieldDescriptor<>(
                    titleKey, type, getter, collection, content, galleryConfig,sectionId
            );
        }
    }
}
