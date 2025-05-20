package regis;


import meta.CrudContext;
import meta.FieldType;
import meta.RuleDescriptor;
import meta.RuleTrigger;
import model.test.Customer;
import model.test.Order;
import model.test.OrderItem;
import model.test.Product;

public class MetaInitializer {

    public static void initialize() {
        registerProduct();
        registerCustomer();
        registerOrder();
    }

    private static void registerProduct() {
        CrudContext<Product> ctx = new CrudContext<Product>(Product.class)
                .field("name", "نام", FieldType.TEXT)
                .field("price", "قیمت", FieldType.NUMBER);
        CrudRegistry.register(ctx);

        // یک Rule نمونه: نام کالا نباید خالی باشد در زمان ذخیره
        RuleDescriptor<Product> nameNotEmptyRule = new RuleDescriptor<>(
                "NameNotEmpty",
                RuleTrigger.ON_SAVE,
                product -> product.getName() != null && !product.getName().trim().isEmpty(),
                product -> { /* اگر شرط نقض شد، خطا یا لاگ بزن */ }
        );
        ctx.addRule(nameNotEmptyRule);
    }

    private static void registerCustomer() {
        CrudContext<Customer> ctx = new CrudContext<>(Customer.class)
                .field("name", "نام", FieldType.TEXT)
                .field("phone", "تلفن", FieldType.TEXT);
        CrudRegistry.register(ctx);
    }

    private static void registerOrder() {
        CrudContext<Order> ctx = new CrudContext<>(Order.class)
                .field("customer", "مشتری", FieldType.LOOKUP)
                .detail("items", "اقلام سفارش", OrderItem.class);
        CrudRegistry.register(ctx);
    }
}
