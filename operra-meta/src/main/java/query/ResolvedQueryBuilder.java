package query;

import base.ContextScope;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;

public class ResolvedQueryBuilder<T> {

    private QueryDescriptor<T> query;
    private final Map<String, Function<ContextScope, Object>> paramBindings = new LinkedHashMap<>();
    private String valueField = "id";
    private Class<?> targetType;
    private Function<Object, ?> entityLoader;

    public static <T> ResolvedQueryBuilder<T> create() {
        return new ResolvedQueryBuilder<>();
    }

    public ResolvedQueryBuilder<T> query(QueryDescriptor<T> query) {
        this.query = query;
        return this;
    }

    public ResolvedQueryBuilder<T> param(String name, Function<ContextScope, Object> provider) {
        this.paramBindings.put(name, provider);
        return this;
    }

    public ResolvedQueryBuilder<T> valueField(String valueField) {
        this.valueField = valueField;
        return this;
    }

    public ResolvedQueryBuilder<T> targetType(Class<?> type) {
        this.targetType = type;
        return this;
    }

    public ResolvedQueryBuilder<T> entityLoader(Function<Object, ?> loader) {
        this.entityLoader = loader;
        return this;
    }

    public ResolvedQuery<T> build() {
        return new ResolvedQuery<>(query, paramBindings, valueField, targetType, entityLoader);
    }
}
