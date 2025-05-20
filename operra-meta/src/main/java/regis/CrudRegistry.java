package regis;


import meta.CrudContext;

import java.util.HashMap;
import java.util.Map;

public class CrudRegistry {

    private static final Map<Class<?>, CrudContext<?>> registry = new HashMap<>();

    public static <T> void register(CrudContext<T> context) {
        registry.put(context.getEntityClass(), context);
    }

    @SuppressWarnings("unchecked")
    public static <T> CrudContext<T> get(Class<T> clazz) {
        return (CrudContext<T>) registry.get(clazz);
    }

    public static Map<Class<?>, CrudContext<?>> getAll() {
        return registry;
    }
}
