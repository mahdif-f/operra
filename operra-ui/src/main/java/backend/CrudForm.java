package backend;


import meta.CrudContext;
import meta.FieldDescriptor;

import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;

public class CrudForm<T> {

    private final CrudContext<T> crudContext;
    private final Map<String, FormField<?>> fields = new LinkedHashMap<>();
    private T entity;

    public CrudForm(CrudContext<T> crudContext) {
        this.crudContext = crudContext;
        this.entity = null;
        initFields();
    }

    private void initFields() {
        for (FieldDescriptor f : crudContext.getFields()) {
            fields.put(f.getName(), new FormField<>(f));
        }
    }

    public Map<String, FormField<?>> getFields() {
        return fields;
    }

    public void loadEntity(T entity) {
        this.entity = entity;
        if (entity == null) return;
        try {
            for (Map.Entry<String, FormField<?>> entry : fields.entrySet()) {
                String name = entry.getKey();
                FormField<?> formField = entry.getValue();
                Method getter = entity.getClass().getMethod("get" + capitalize(name));
                Object value = getter.invoke(entity);
                // unchecked cast safe by design (تایپ‌سیف در متادیتا)
                ((FormField<Object>) formField).setValue(value);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error loading entity into form", e);
        }
    }

    public T saveEntity() {
        if (entity == null) {
            try {
                entity = crudContext.getEntityClass().getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                throw new RuntimeException("Error creating new entity instance", e);
            }
        }
        try {
            for (Map.Entry<String, FormField<?>> entry : fields.entrySet()) {
                String name = entry.getKey();
                FormField<?> formField = entry.getValue();
                Method setter = entity.getClass().getMethod("set" + capitalize(name));
//                , formField.getType()
                setter.invoke(entity, formField.getValue());
            }
        } catch (Exception e) {
            throw new RuntimeException("Error saving form to entity", e);
        }
        return entity;
    }

    private String capitalize(String str) {
        if (str == null || str.isEmpty()) return str;
        return Character.toUpperCase(str.charAt(0)) + str.substring(1);
    }
}
