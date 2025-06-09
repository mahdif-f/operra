package base;

import java.util.Locale;
import java.util.Map;
import java.util.Optional;

public class ConditionContextFromScope implements ContextualCondition.ConditionContext {

    private final ContextScope scope;
    private final String contextId;

    public ConditionContextFromScope(ContextScope scope, String contextId) {
        this.scope = scope;
        this.contextId = contextId;
    }

    @Override
    public Object getEntity() {
        return scope.getCurrentEntity();
    }

    @Override
    public String getContextId() {
        return contextId;
    }

    @Override
    public Optional<String> getCurrentUserRole() {
        Object role = scope.fromSession("role");
        return role instanceof String ? Optional.of((String) role) : Optional.empty();
    }

    @Override
    public Optional<Locale> getLocale() {
        Object loc = scope.fromSystem("locale");
        return loc instanceof Locale ? Optional.of((Locale) loc) : Optional.empty();
    }

    @Override
    public Map<String, Object> getAttributes() {
        return scope.fromUserInput("attributes") instanceof Map m ? m : Map.of();
    }
}
