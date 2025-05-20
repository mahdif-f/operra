package service;


import meta.*;

import java.util.List;
import java.util.Map;

public interface CrudMetadataService {
    <T> CrudContext<T> getCrudContext(Class<T> entityClass);

    <T> List<FieldDescriptor> getFields(Class<T> entityClass);

    <T> List<RuleDescriptor<T>> getRules(Class<T> entityClass);

    <T> Map<String, LookupDescriptor> getLookups(Class<T> entityClass);
}
