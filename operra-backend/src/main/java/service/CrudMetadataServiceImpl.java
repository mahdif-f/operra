package service;


import jakarta.enterprise.context.ApplicationScoped;
import meta.*;
import regis.CrudRegistry;

import java.util.List;
import java.util.Map;

@ApplicationScoped
public class CrudMetadataServiceImpl implements CrudMetadataService {

    private final CrudRegistry registry = new CrudRegistry();

    @Override
    public <T> CrudContext<T> getCrudContext(Class<T> entityClass) {
        CrudContext<T> context = registry.get(entityClass);
        if (context == null) {
            throw new IllegalArgumentException("No CrudContext registered for " + entityClass.getName());
        }
        return context;
    }

    @Override
    public <T> List<FieldDescriptor> getFields(Class<T> entityClass) {
        CrudContext<T> context = getCrudContext(entityClass);
        return context.getFields();
    }

    @Override
    public <T> List<RuleDescriptor<T>> getRules(Class<T> entityClass) {
        CrudContext<T> context = getCrudContext(entityClass);
        return context.getRules();
    }

    @Override
    public <T> Map<String, LookupDescriptor> getLookups(Class<T> entityClass) {
        CrudContext<T> context = getCrudContext(entityClass);
        return context.getLookups();
    }
}
