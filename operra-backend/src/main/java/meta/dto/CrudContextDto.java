package meta.dto;

import java.util.List;


public class CrudContextDto {
    private String title;
    private String formType;
    private List<FieldDto> fields;
    private List<RuleDto> rules;
    private List<LookupDto> lookups;

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getFormType() { return formType; }
    public void setFormType(String formType) { this.formType = formType; }

    public List<FieldDto> getFields() { return fields; }
    public void setFields(List<FieldDto> fields) { this.fields = fields; }

    public List<RuleDto> getRules() { return rules; }
    public void setRules(List<RuleDto> rules) { this.rules = rules; }

    public List<LookupDto> getLookups() { return lookups; }
    public void setLookups(List<LookupDto> lookups) { this.lookups = lookups; }
}