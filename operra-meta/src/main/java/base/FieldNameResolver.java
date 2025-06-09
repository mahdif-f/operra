package base;

import java.util.function.Function;

/**
 * ابزار استخراج نام فیلد از lambda getter
 * پیاده‌سازی پایه مبتنی بر method reference با نام متد getX → x
 */
public class FieldNameResolver {

    public static <T, V> String resolve(Function<T, V> getter) {
        String methodRef = getter.toString();

        if (!methodRef.contains("::")) {
            throw new IllegalArgumentException("Getter must be a method reference like Entity::getName");
        }

        String[] parts = methodRef.split("::");
        String methodName = parts[1];

        if (methodName.startsWith("get") && methodName.length() > 3) {
            return decapitalize(methodName.substring(3));
        } else if (methodName.startsWith("is") && methodName.length() > 2) {
            return decapitalize(methodName.substring(2));
        } else {
            return methodName;
        }
    }

    public static <T, V> String resolveName(Function<T, V> getter) {
        return resolve(getter);
    }

    private static String decapitalize(String str) {
        if (str == null || str.isEmpty()) return str;
        return str.substring(0, 1).toLowerCase() + str.substring(1);
    }
}
