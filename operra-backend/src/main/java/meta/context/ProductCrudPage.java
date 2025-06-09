package meta.context;

import meta.*;
import meta.core.CrudContext;
import meta.ui.UiSectionType;
import model.test.Product;
import util.I18n;

public class ProductCrudPage extends CrudContext<Product> {

//    public ProductCrudPage() {
//        super(Product.class);
//
//        this.title(I18n.get("product.form.title"))
//                .formType(FormType.CRUD)
//                .layout(LayoutType.GRID, 2)
//
//                // گروهبندی فیلدها در تب مشخصات عمومی
//                .section(new SectionDescriptor("general", I18n.get("product.section.general"), UiSectionType.TAB)
//                        .addField("code")
//                        .addField("name")
//                        .addField("category"))
//
//                // گروهبندی فیلدها در تب قیمت و وضعیت
//                .section(new SectionDescriptor("price", I18n.get("product.section.price"), UiSectionType.TAB)
//                        .addField("price")
//                        .addField("active"))
//
//                // تعریف فیلدها
//                .field(new FieldDescriptor("code", I18n.get("product.code"), FieldType.TEXT)
//                        .required(true)
//                        .placeholder(I18n.get("product.code.placeholder")))
//
//                .field(new FieldDescriptor("name", I18n.get("product.name"), FieldType.TEXT)
//                        .required(true)
//                        .placeholder(I18n.get("product.name.placeholder")))
//
//                .field(new FieldDescriptor("category", I18n.get("product.category"), FieldType.REFERENCE)
//                        .lookupId("categoryLookup")
//                        .required(true)
//                        .placeholder(I18n.get("product.category.placeholder")))
//
//                .field(new FieldDescriptor("price", I18n.get("product.price"), FieldType.DECIMAL)
//                        .required(true)
//                        .min(0)
//                        .placeholder(I18n.get("product.price.placeholder")))
//
//                .field(new FieldDescriptor("active", I18n.get("product.active"), FieldType.BOOLEAN)
//                        .defaultValue("true"))
//
//                // Rule برای الزام مقدار دهی کد و قیمت
//                .addRule(new RuleDescriptor<>(
//                        "required-code", RuleType.REQUIRED, "code",
//                        "!#code.isEmpty()", I18n.get("product.code.required"), ActionTriggerType.ON_SUBMIT))
//
//                .addRule(new RuleDescriptor<>(
//                        "positive-price", RuleType.RANGE, "price",
//                        "#price > 0", I18n.get("product.price.positive"), ActionTriggerType.ON_SUBMIT))
//
//                // اکشن کاستوم چاپ فاکتور
//                .action(new ActionDescriptor("print", I18n.get("product.action.print"), ActionType.PRINT)
//                        .withIcon("pi pi-print"));
//    }
}
