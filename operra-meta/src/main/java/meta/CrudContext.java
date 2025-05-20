package meta;


import java.util.*;

public class CrudContext<T> {
    private final Class<T> entityType;
    private final List<FieldDescriptor> fields = new ArrayList<>();
    private final List<RuleDescriptor> rules = new ArrayList<>();
    private final Map<String, LookupDescriptor> lookups = new HashMap<>();
    private final List<DetailDescriptor> details = new ArrayList<>();

    public CrudContext(Class<T> entityType) {
        this.entityType = entityType;
    }

    public Class<T> getEntityClass() {
        return entityType;
    }
    public CrudContext<T> field(String name, String label, FieldType type) {
        fields.add(new FieldDescriptor(name, label, type));
        return this;
    }

    public Class<T> getEntityType() {
        return entityType;
    }

    public List<FieldDescriptor> getFields() {
        return fields;
    }


    public void addRule(RuleDescriptor<T> rule) {
        rules.add(rule);
    }

    public List<RuleDescriptor<T>> getRules() {
        return Collections.unmodifiableList(rules);
    }

    // افزودن Rule پیچیده (با flag complex)
//    public CrudContext<T> complexRule(String targetField, RuleTrigger trigger, String expression) {
//        rules.add(new RuleDescriptor(targetField, trigger, expression, true));
//        return this;
//    }

    // افزودن Lookup برای یک فیلد
    public CrudContext<T> lookup(String fieldName, LookupDescriptor descriptor) {
        lookups.put(fieldName, descriptor);
        return this;
    }

    public Map<String, LookupDescriptor> getLookups() {
        return lookups;
    }


    public CrudContext<T> detail(String fieldName, String title, Class<?> detailClass) {
        details.add(new DetailDescriptor(fieldName, title, detailClass));
        return this;
    }

    public List<DetailDescriptor> getDetails() {
        return details;
    }
}
