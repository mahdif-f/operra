package meta.dto;

public class RuleDto {
    private String type;
    private String targetField;
    private String expression;
    private String trigger;

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getTargetField() { return targetField; }
    public void setTargetField(String targetField) { this.targetField = targetField; }

    public String getExpression() { return expression; }
    public void setExpression(String expression) { this.expression = expression; }

    public String getTrigger() { return trigger; }
    public void setTrigger(String trigger) { this.trigger = trigger; }
}