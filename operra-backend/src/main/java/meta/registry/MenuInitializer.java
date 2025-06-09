package meta.registry;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import meta.core.CrudContext;
import meta.context.ProductCrudPage;
import meta.menu.MenuItemDescriptor;


@ApplicationScoped
public class MenuInitializer {

    @Inject
    MenuRegistry registry;

    @Inject
    CrudRegistry crudRegistry;

    @PostConstruct
    public void init() {
        registry.getMenu()
                .addItem(new MenuItemDescriptor("کالاها").context(ProductCrudPage.class));
//                .addItem(new MenuItemDescriptor("سفارش‌ها").context(OrderCrudContext.class))
//                .addItem(new MenuItemDescriptor("مدیریت")
//                        .child(new MenuItemDescriptor("کاربران").context(UserCrudContext.class))
//                        .child(new MenuItemDescriptor("دسترسی‌ها").context(PermissionCrudContext.class))
//                );

        // رجیستر کردن خودکار contextها از روی منو
        for (MenuItemDescriptor item : registry.getMenu().getItems()) {
            registerFromMenu(item);
        }
    }

    private void registerFromMenu(MenuItemDescriptor item) {
        if (item.getContextClass() != null) {
            try {
                CrudContext<?> ctx = item.getContextClass()
                        .getDeclaredConstructor()
                        .newInstance()
                        .build();
                crudRegistry.register(ctx);
            } catch (Exception e) {
                throw new RuntimeException("خطا در ایجاد کانتکست برای: " + item.getLabel(), e);
            }
        }
        for (MenuItemDescriptor child : item.getChildren()) {
            registerFromMenu(child);
        }
    }
}