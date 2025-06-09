package core;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import meta.core.CrudContext;
import meta.registry.CrudRegistry;

import java.util.List;
import java.util.function.Consumer;


@ApplicationScoped
public class CrudEngine {

    @PersistenceContext
    EntityManager em;

    @Inject
    CrudRegistry registry;

    private final RuleEvaluator evaluator = new RuleEvaluator();

    public <T> List<T> list(Class<T> entityClass) {
        return em.createQuery("SELECT e FROM " + entityClass.getSimpleName() + " e", entityClass)
                .getResultList();
    }

    public <T> T get(Class<T> entityClass, Object id) {
        return em.find(entityClass, id);
    }

    @Transactional
    public <T> T create(Class<T> entityClass, T entity) {
        CrudContext<T> context = registry.getContext(entityClass);
//        validateRules(context, entity);
        Consumer<T> before = context.getBeforeCreate();
        if (before != null) before.accept(entity);
        em.persist(entity);
        Consumer<T> after = context.getAfterCreate();
        if (after != null) after.accept(entity);
        return entity;
    }

    @Transactional
    public <T> T update(Class<T> entityClass, T entity) {
        CrudContext<T> context = registry.getContext(entityClass);
//        validateRules(context, entity);
        Consumer<T> before = context.getBeforeUpdate();
        if (before != null) before.accept(entity);
        T merged = em.merge(entity);
        Consumer<T> after = context.getAfterUpdate();
        if (after != null) after.accept(merged);
        return merged;
    }

    @Transactional
    public <T> void delete(Class<T> entityClass, Object id) {
        T entity = em.find(entityClass, id);
        CrudContext<T> context = registry.getContext(entityClass);
        Consumer<T> before = context.getBeforeDelete();
        if (before != null) before.accept(entity);
        em.remove(entity);
        Consumer<T> after = context.getAfterDelete();
        if (after != null) after.accept(entity);
    }

    private <T> void validateRules(CrudContext<T> context, T entity) {
        ValidationException errors = new ValidationException(null, null);
        for (RuleDescriptor<T> rule : context.getRules()) {
            boolean ok = evaluator.evaluate(rule, entity);
            if (!ok) {
                errors.add(rule.getTargetField(), rule.getMessage());
            }
        }
        if (!errors.getMessages().isEmpty()) {
            throw errors;
        }
    }
}