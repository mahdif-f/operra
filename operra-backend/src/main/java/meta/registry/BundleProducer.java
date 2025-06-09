package meta.registry;

import jakarta.enterprise.context.ApplicationScoped;
import java.util.Locale;
import java.util.ResourceBundle;

// ✅ کلاس BundleProducer: تولیدکننده ResourceBundle برای i18n

@ApplicationScoped
public class BundleProducer {
    public ResourceBundle getBundle() {
        return ResourceBundle.getBundle("messages", Locale.getDefault());
    }
}
