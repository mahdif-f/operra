package ui;

import base.ContextualCondition;

import java.util.*;

/**
 * توصیف متادیتای یک بخش (Section) در فرم‌های داینامیک ERP.
 * این سکشن می‌تواند به‌صورت تب، پنل، گروه فیلد و... در UI نمایش یابد.
 * <p>
 * فیلدهایی که در این سکشن قرار می‌گیرند، از طریق پراپرتی {@code sectionId} در FieldDescriptor به این بخش متصل می‌شوند.
 * این کلاس فقط متادیتای ساختاری را نگهداری می‌کند.
 *
 * ویژگی‌ها:
 * <ul>
 *     <li>پشتیبانی از layout و breakpoints واکنش‌گرا</li>
 *     <li>قابلیت تو در تو بودن سکشن‌ها</li>
 *     <li>قابلیت تعیین شرط نمایش با ContextualCondition</li>
 * </ul>
 */
public class UiSectionDescriptor {

    /** شناسه یکتای سکشن */
    private final String id;

    /** عنوان نمایشی سکشن (کلید ترجمه‌شده از ResourceBundle) */
    private final String title;

    /** نوع layout سکشن (مانند TAB, PANEL, GROUP و ...) */
    private final UiSectionLayoutType layoutType;

    /** لیست سکشن‌های فرزند (در صورت وجود) */
    private final List<UiSectionDescriptor> children;

    /** شرط نمایش سکشن بر اساس context */
    private final ContextualCondition visibleWhen;

    /** آیکن نمایشی (اختیاری) */
    private final String icon;

    /** تعداد ستون‌ها برای چیدمان داخلی */
    private final int columns;

    /** breakpoints واکنش‌گرا */
    private final String responsiveBreakpoints;

    /** فاصله بین آیتم‌ها (gap) */
    private final String gap;

    // --- Constructor خصوصی ---
    private UiSectionDescriptor(
            String id,
            String title,
            UiSectionLayoutType layoutType,
            List<UiSectionDescriptor> children,
            ContextualCondition visibleWhen,
            String icon,
            int columns,
            String responsiveBreakpoints,
            String gap
    ) {
        this.id = id;
        this.title = title;
        this.layoutType = layoutType;
        this.children = children;
        this.visibleWhen = visibleWhen;
        this.icon = icon;
        this.columns = columns;
        this.responsiveBreakpoints = responsiveBreakpoints;
        this.gap = gap;
    }

    // --- Getters ---
    public String getId() { return id; }
    public String getTitle() { return title; }
    public UiSectionLayoutType getLayoutType() { return layoutType; }
    public List<UiSectionDescriptor> getChildren() { return children; }
    public ContextualCondition getVisibleWhen() { return visibleWhen; }
    public String getIcon() { return icon; }
    public int getColumns() { return columns; }
    public String getResponsiveBreakpoints() { return responsiveBreakpoints; }
    public String getGap() { return gap; }

    // --- Builder Factory ---
    public static Builder builder(String id, String title, UiSectionLayoutType layout) {
        return new Builder(id, title, layout);
    }

    /**
     * Builder دکلریتیو برای ساخت سکشن با ساختار تو در تو و شرطی
     */
    public static class Builder {
        private final String id;
        private final String title;
        private final UiSectionLayoutType layoutType;
        private final List<UiSectionDescriptor> children = new ArrayList<>();
        private ContextualCondition visibleWhen = ctx -> true;
        private String icon;
        private int columns = 2;
        private String responsiveBreakpoints = "sm:1 md:2 lg:2";
        private String gap = "1rem";

        public Builder(String id, String title, UiSectionLayoutType layoutType) {
            this.id = id;
            this.title = title;
            this.layoutType = layoutType;
        }

        /** افزودن سکشن فرزند */
        public Builder addChild(UiSectionDescriptor child) {
            this.children.add(child);
            return this;
        }

        /** شرط نمایش سکشن (بر اساس context) */
        public Builder visibleWhen(ContextualCondition condition) {
            this.visibleWhen = condition;
            return this;
        }

        /** تعیین آیکن نمایشی */
        public Builder icon(String icon) {
            this.icon = icon;
            return this;
        }

        /** تعداد ستون‌ها برای چیدمان */
        public Builder columns(int columns) {
            this.columns = columns;
            return this;
        }

        /** تنظیم نقاط شکست واکنش‌گرا */
        public Builder responsiveBreakpoints(String breakpoints) {
            this.responsiveBreakpoints = breakpoints;
            return this;
        }

        /** تعیین فاصله بین آیتم‌های داخل سکشن */
        public Builder gap(String gap) {
            this.gap = gap;
            return this;
        }

        /** ساخت نهایی سکشن */
        public UiSectionDescriptor build() {
            return new UiSectionDescriptor(
                    id, title, layoutType, children,
                    visibleWhen, icon,
                    columns, responsiveBreakpoints, gap
            );
        }
    }
}
