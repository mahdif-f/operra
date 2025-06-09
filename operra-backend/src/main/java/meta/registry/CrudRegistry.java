package meta.registry;


import jakarta.enterprise.context.ApplicationScoped;
import meta.core.CrudContext;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

// ✅ کلاس CrudRegistry: رجیستری برای نگهداری CrudContext همه انتیتی‌ها به صورت singleton
@ApplicationScoped
public class CrudRegistry {

    // نگاشت بین کلاس انتیتی و کانتکست آن
    private final Map<Class<?>, CrudContext<?>> registry = new ConcurrentHashMap<>();

    // نگاشت بین کلاس کانتکست و contextId (برای استفاده در منو)
    private final Map<Class<? extends CrudContext<?>>, String> contextClassToId = new HashMap<>();

    /**
     * ثبت یک کانتکست جدید در رجیستری
     */
    @SuppressWarnings("unchecked")
    public <T> void register(CrudContext<T> context) {
        registry.put(context.getEntityType(), context);
        Class<? extends CrudContext<?>> ctxClass = (Class<? extends CrudContext<?>>) context.getClass();
        contextClassToId.put(ctxClass, context.getContextId());
    }

    /**
     * بازیابی کانتکست با استفاده از کلاس انتیتی
     */
    @SuppressWarnings("unchecked")
    public <T> CrudContext<T> getContext(Class<T> entityClass) {
        return (CrudContext<T>) registry.get(entityClass);
    }

    /**
     * بازیابی کانتکست با استفاده از نام انتیتی (مثلاً از URL)
     */
    public CrudContext<?> getContext(String entityName) {
        return getAll().values().stream()
                .filter(c -> c.getEntityType().getSimpleName().equalsIgnoreCase(entityName))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Context not found for " + entityName));
    }

    /**
     * بررسی وجود کانتکست برای کلاس انتیتی
     */
    public boolean contains(Class<?> entityClass) {
        return registry.containsKey(entityClass);
    }

    /**
     * دریافت همه کانتکست‌ها
     */
    public Map<Class<?>, CrudContext<?>> getAll() {
        return Collections.unmodifiableMap(registry);
    }

    /**
     * دریافت contextId با استفاده از کلاس کانتکست
     */
    public String getId(Class<? extends CrudContext<?>> contextClass) {
        String id = contextClassToId.get(contextClass);
        if (id == null) {
            throw new IllegalArgumentException("کانتکست با کلاس مشخص‌شده یافت نشد: " + contextClass.getName());
        }
        return id;
    }
}