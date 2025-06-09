package page.dataview;

import page.PageDescriptor;

import java.util.ArrayList;
import java.util.List;

/**
 * توصیف‌گر صفحه‌ای از نوع DataView برای نمایش لیست داده‌ها، نمودارها، جدول‌های محوری و سایر گزارش‌ها.
 * این صفحه می‌تواند شامل چندین بلاک (DataViewBlock) باشد که در UI به صورت سکشن‌بندی‌شده نمایش داده می‌شوند.
 */
public class DataViewPageDescriptor extends PageDescriptor {

    private final List<DataViewBlockDescriptor> blocks;

    public DataViewPageDescriptor(String pageId, String title, String helpText,
                                  List<DataViewBlockDescriptor> blocks) {
        this.pageId = pageId;
        this.title = title;
        this.helpText = helpText;
        this.blocks = blocks;
    }

    public List<DataViewBlockDescriptor> getBlocks() {
        return blocks;
    }

    public static Builder builder(String pageId, String title) {
        return new Builder(pageId, title);
    }

    /**
     * Builder برای ساخت صفحه DataView به‌صورت DSL و دکلریتیو.
     */
    public static class Builder {
        private final String pageId;
        private final String title;
        private String helpText = "";
        private final List<DataViewBlockDescriptor> blocks = new ArrayList<>();

        public Builder(String pageId, String title) {
            this.pageId = pageId;
            this.title = title;
        }

        public Builder helpText(String helpText) {
            this.helpText = helpText;
            return this;
        }

        public Builder addBlock(DataViewBlockDescriptor block) {
            this.blocks.add(block);
            return this;
        }

        public Builder addBlocks(List<DataViewBlockDescriptor> blocks) {
            this.blocks.addAll(blocks);
            return this;
        }

        public DataViewPageDescriptor build() {
            return new DataViewPageDescriptor(pageId, title, helpText, blocks);
        }
    }
}

