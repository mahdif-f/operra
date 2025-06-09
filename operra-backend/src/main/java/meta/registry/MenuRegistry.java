package meta.registry;

import jakarta.enterprise.context.ApplicationScoped;
import meta.menu.MenuDescriptor;

@ApplicationScoped
public class MenuRegistry {

    private final MenuDescriptor menu = new MenuDescriptor();

    public MenuDescriptor getMenu() {
        return menu;
    }
}