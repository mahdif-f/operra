package page.cms;


import base.ContextualCondition;
import page.PageDescriptor;

/**
 * توصیف‌گر یک صفحه CMS (محتوامحور) برای استفاده در سیستم ERP.
 *
 * <p>
 * این کلاس فقط اطلاعات متا در مورد یک صفحه CMS خارجی را نگه می‌دارد.
 * محتوا از ابزار خارجی مانند <b>Strapi CMS</b> دریافت می‌شود.
 * </p>
 *
 * <p>هدف استفاده:</p>
 * <ul>
 *     <li>صفحات راهنما، درباره ما، landing page</li>
 *     <li>قابل نمایش در منوها، با کنترل دسترسی</li>
 *     <li>پشتیبانی از انواع نمایش: markdown، html، iframe و لینک خارجی</li>
 * </ul>
 *
 * <p>اجرای عملیاتی این کلاس در آینده در سطح meta-runtime و front انجام خواهد شد.</p>
 *
 * @author Operra
 */
public class CmsPageDescriptor extends PageDescriptor {

    /** شناسه صفحه در CMS (مانند slug یا ID در Strapi) */
    private final String externalPageId;

    /** نوع صفحه برای تعیین نحوه نمایش در front */
    private final CmsPageType type;

    /** شرط نمایش این صفحه برای کاربران مختلف */
    private final ContextualCondition visibilityRule;

    /** آدرس URL در صورت نمایش iframe */
    private final String iframeUrl;

    /** محتوای پیش‌فرض یا fallback در صورت عدم دریافت از CMS */
    private final String fallbackContent;

    private CmsPageDescriptor(Builder builder) {
        this.pageId = builder.pageId;
        this.title = builder.title;
        this.helpText = builder.helpText;
        this.externalPageId = builder.externalPageId;
        this.type = builder.type;
        this.visibilityRule = builder.visibilityRule;
        this.iframeUrl = builder.iframeUrl;
        this.fallbackContent = builder.fallbackContent;
    }

    public String getExternalPageId() {
        return externalPageId;
    }

    public CmsPageType getType() {
        return type;
    }

    public ContextualCondition getVisibilityRule() {
        return visibilityRule;
    }

    public String getIframeUrl() {
        return iframeUrl;
    }

    public String getFallbackContent() {
        return fallbackContent;
    }

    /**
     * نوع نمایش صفحه CMS در front
     */
    public enum CmsPageType {
        MARKDOWN,
        HTML,
        IFRAME,
        EXTERNAL_LINK
    }

    /**
     * بیلدر برای ساخت توصیف‌گر صفحه CMS
     */
    public static class Builder {
        private String pageId;
        private String title;
        private String helpText;
        private String externalPageId;
        private CmsPageType type = CmsPageType.MARKDOWN;
        private ContextualCondition visibilityRule = ContextualCondition.alwaysTrue();
        private String iframeUrl;
        private String fallbackContent;

        public Builder pageId(String pageId) {
            this.pageId = pageId;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder helpText(String helpText) {
            this.helpText = helpText;
            return this;
        }

        public Builder externalPageId(String externalPageId) {
            this.externalPageId = externalPageId;
            return this;
        }

        public Builder type(CmsPageType type) {
            this.type = type;
            return this;
        }

        public Builder visibilityRule(ContextualCondition rule) {
            this.visibilityRule = rule;
            return this;
        }

        public Builder iframeUrl(String iframeUrl) {
            this.iframeUrl = iframeUrl;
            return this;
        }

        public Builder fallbackContent(String fallbackContent) {
            this.fallbackContent = fallbackContent;
            return this;
        }

        public CmsPageDescriptor build() {
            return new CmsPageDescriptor(this);
        }
    }
}
