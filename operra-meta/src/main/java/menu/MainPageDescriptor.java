package menu;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * توصیف‌گر صفحه اصلی شامل منو، لوگو، نوتیفیکیشن، نام کاربر و پیام‌های سیستمی
 */
public class MainPageDescriptor {

    private final List<MenuItemDescriptor> menuItems;
    private final Supplier<String> logoUrlSupplier;
    private final Supplier<List<String>> notificationSupplier;
    private final Supplier<String> userDisplayNameSupplier;
    private final Supplier<List<String>> systemMessagesSupplier;

    private MainPageDescriptor(List<MenuItemDescriptor> menuItems,
                               Supplier<String> logoUrlSupplier,
                               Supplier<List<String>> notificationSupplier,
                               Supplier<String> userDisplayNameSupplier,
                               Supplier<List<String>> systemMessagesSupplier) {
        this.menuItems = menuItems;
        this.logoUrlSupplier = logoUrlSupplier;
        this.notificationSupplier = notificationSupplier;
        this.userDisplayNameSupplier = userDisplayNameSupplier;
        this.systemMessagesSupplier = systemMessagesSupplier;
    }

    public List<MenuItemDescriptor> getMenuItems() {
        return menuItems;
    }

    public Supplier<String> getLogoUrlSupplier() {
        return logoUrlSupplier;
    }

    public Supplier<List<String>> getNotificationSupplier() {
        return notificationSupplier;
    }

    public Supplier<String> getUserDisplayNameSupplier() {
        return userDisplayNameSupplier;
    }

    public Supplier<List<String>> getSystemMessagesSupplier() {
        return systemMessagesSupplier;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final List<MenuItemDescriptor> menuItems = new ArrayList<>();
        private Supplier<String> logoUrlSupplier = () -> "";
        private Supplier<List<String>> notificationSupplier = ArrayList::new;
        private Supplier<String> userDisplayNameSupplier = () -> "";
        private Supplier<List<String>> systemMessagesSupplier = ArrayList::new;

        public Builder addMenuItem(MenuItemDescriptor item) {
            this.menuItems.add(item);
            return this;
        }

        public Builder logoUrl(Supplier<String> logoSupplier) {
            this.logoUrlSupplier = logoSupplier;
            return this;
        }

        public Builder notifications(Supplier<List<String>> notifications) {
            this.notificationSupplier = notifications;
            return this;
        }

        public Builder userDisplayName(Supplier<String> displayNameSupplier) {
            this.userDisplayNameSupplier = displayNameSupplier;
            return this;
        }

        public Builder systemMessages(Supplier<List<String>> messagesSupplier) {
            this.systemMessagesSupplier = messagesSupplier;
            return this;
        }

        public MainPageDescriptor build() {
            return new MainPageDescriptor(menuItems, logoUrlSupplier, notificationSupplier, userDisplayNameSupplier, systemMessagesSupplier);
        }
    }
}
