package menu;




import page.PageDescriptor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;


/**
 * تعریف یک آیتم منو به‌صورت دکلرتیو و بازگشتی، با شرط نمایش و مجوزهای دسترسی
 */
public class MenuItemDescriptor {

    private final String id;
    private final String titleKey;
    private final String icon;
    private final PageDescriptor context;
    private final List<MenuItemDescriptor> children;
    private final Supplier<Boolean> visibleWhen;
    private final Set<String> requiredPermissions;

    private MenuItemDescriptor(String id,
                               String titleKey,
                               String icon,
                               PageDescriptor context,
                               List<MenuItemDescriptor> children,
                               Supplier<Boolean> visibleWhen,
                               Set<String> requiredPermissions) {
        this.id = id;
        this.titleKey = titleKey;
        this.icon = icon;
        this.context = context;
        this.children = children;
        this.visibleWhen = visibleWhen;
        this.requiredPermissions = requiredPermissions;
    }

    public String getId() {
        return id;
    }

    public String getTitleKey() {
        return titleKey;
    }

    public String getIcon() {
        return icon;
    }

    public PageDescriptor getContext() {
        return context;
    }

    public List<MenuItemDescriptor> getChildren() {
        return children;
    }

    public Supplier<Boolean> getVisibleWhen() {
        return visibleWhen;
    }

    public Set<String> getRequiredPermissions() {
        return requiredPermissions;
    }

    public static MenuItemDescriptorBuilder builder(String id, String titleKey) {
        return new MenuItemDescriptorBuilder(id, titleKey);
    }

    public static class MenuItemDescriptorBuilder {
        private final String id;
        private final String titleKey;
        private String icon;
        private PageDescriptor context;
        private final List<MenuItemDescriptor> children = new ArrayList<>();
        private Supplier<Boolean> visibleWhen = () -> true;
        private final Set<String> requiredPermissions = new HashSet<>();

        public MenuItemDescriptorBuilder(String id, String titleKey) {
            this.id = id;
            this.titleKey = titleKey;
        }

        public MenuItemDescriptorBuilder icon(String icon) {
            this.icon = icon;
            return this;
        }

        public MenuItemDescriptorBuilder context(PageDescriptor context) {
            this.context = context;
            return this;
        }

        public MenuItemDescriptorBuilder addChild(MenuItemDescriptor child) {
            this.children.add(child);
            return this;
        }

        public MenuItemDescriptorBuilder visibleWhen(Supplier<Boolean> condition) {
            this.visibleWhen = condition;
            return this;
        }

        public MenuItemDescriptorBuilder requirePermission(String permission) {
            this.requiredPermissions.add(permission);
            return this;
        }

        public MenuItemDescriptor build() {
            return new MenuItemDescriptor(id, titleKey, icon, context, children, visibleWhen, requiredPermissions);
        }
    }
}