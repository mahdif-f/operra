package core;


import org.mvel2.MVEL;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class RuleEvaluator {

    public <T> boolean evaluate(RuleDescriptor<T> rule, T entity) {
        Map<String, Object> vars = new HashMap<>();
        vars.put("#", entity);  // نشانه shorthand برای entity کامل
        for (java.lang.reflect.Method method : entity.getClass().getMethods()) {
            if (method.getName().startsWith("get") && method.getParameterCount() == 0) {
                try {
                    String name = method.getName().substring(3);
                    name = Character.toLowerCase(name.charAt(0)) + name.substring(1);
                    vars.put(name, method.invoke(entity));
                } catch (Exception ignored) {}
            }
        }
        Serializable compiled = MVEL.compileExpression(rule.getExpression());
        Object result = MVEL.executeExpression(compiled, vars);
        return Boolean.TRUE.equals(result);
    }
}
