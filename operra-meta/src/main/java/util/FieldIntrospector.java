package util;


import jakarta.persistence.*;
import java.beans.Introspector;
import java.lang.reflect.*;
import java.util.*;

public final class FieldIntrospector {

    private FieldIntrospector() {
        // Utility class
    }

    /**
     * Extracts a list of field metadata from a given class for form generation.
     *
     * @param clazz the entity class
     * @return list of field metadata
     */
    public static List<FieldMeta> extractFieldMeta(Class<?> clazz) {
        List<FieldMeta> result = new ArrayList<>();

        for (Field field : clazz.getDeclaredFields()) {
            if (Modifier.isStatic(field.getModifiers())) continue;

            String name = field.getName();
            Class<?> type = field.getType();

            boolean isReference = field.isAnnotationPresent(ManyToOne.class) || field.isAnnotationPresent(OneToOne.class);
            boolean isCollection = Collection.class.isAssignableFrom(type);

            CascadeType[] cascade = extractCascade(field);
            RelationType relationType = extractRelationType(field);

            Method getter = resolveGetter(clazz, field);
            if (getter == null) continue;

            result.add(new FieldMeta(name, type, getter, cascade, relationType, isCollection));
        }

        return result;
    }

    private static CascadeType[] extractCascade(Field field) {
        if (field.isAnnotationPresent(OneToMany.class))
            return field.getAnnotation(OneToMany.class).cascade();
        if (field.isAnnotationPresent(ManyToOne.class))
            return field.getAnnotation(ManyToOne.class).cascade();
        if (field.isAnnotationPresent(OneToOne.class))
            return field.getAnnotation(OneToOne.class).cascade();
        if (field.isAnnotationPresent(ManyToMany.class))
            return field.getAnnotation(ManyToMany.class).cascade();
        return new CascadeType[0];
    }

    private static RelationType extractRelationType(Field field) {
        if (field.isAnnotationPresent(OneToMany.class)) return RelationType.ONE_TO_MANY;
        if (field.isAnnotationPresent(ManyToOne.class)) return RelationType.MANY_TO_ONE;
        if (field.isAnnotationPresent(OneToOne.class)) return RelationType.ONE_TO_ONE;
        if (field.isAnnotationPresent(ManyToMany.class)) return RelationType.MANY_TO_MANY;
        return RelationType.NONE;
    }

    private static Method resolveGetter(Class<?> clazz, Field field) {
        String name = field.getName();
        String getterName = (field.getType() == boolean.class || field.getType() == Boolean.class)
                ? "is" + capitalize(name)
                : "get" + capitalize(name);

        try {
            return clazz.getMethod(getterName);
        } catch (NoSuchMethodException e) {
            return null;
        }
    }

    private static String capitalize(String name) {
        return Character.toUpperCase(name.charAt(0)) + name.substring(1);
    }

    /**
     * Describes a field's metadata for page/form generation.
     */
    public static class FieldMeta {
        public final String name;
        public final Class<?> type;
        public final Method getter;
        public final CascadeType[] cascadeTypes;
        public final RelationType relationType;
        public final boolean isCollection;

        public FieldMeta(String name, Class<?> type, Method getter,
                         CascadeType[] cascadeTypes, RelationType relationType, boolean isCollection) {
            this.name = name;
            this.type = type;
            this.getter = getter;
            this.cascadeTypes = cascadeTypes;
            this.relationType = relationType;
            this.isCollection = isCollection;
        }
    }

    /**
     * Enum to describe relation types between entities.
     */
    public enum RelationType {
        NONE, ONE_TO_MANY, MANY_TO_ONE, ONE_TO_ONE, MANY_TO_MANY
    }
}
