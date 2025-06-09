package util;


import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class I18n {

    private static final String BASE_NAME = "messages.messages"; // مسیر باندل (messages.properties)
    private static final Locale DEFAULT_LOCALE = Locale.forLanguageTag("en"); // زبان پیش‌فرض

    public static String get(String key, Object... params) {
        Locale locale = getUserLocale(); // می‌توان این را قابل تنظیم کرد
        ResourceBundle bundle;

        try {
            bundle = ResourceBundle.getBundle(BASE_NAME, locale);
        } catch (MissingResourceException e) {
            // اگر باندل با زبان خواسته‌شده موجود نبود، از پیش‌فرض استفاده می‌کنیم
            try {
                bundle = ResourceBundle.getBundle(BASE_NAME, DEFAULT_LOCALE);
            } catch (MissingResourceException ex) {
                return "[missing bundle]";
            }
        }

        try {
            String pattern = bundle.getString(key);
            return MessageFormat.format(pattern, params);
        } catch (MissingResourceException e) {
            return "[!" + key + "!]";
        }
    }

    private static Locale getUserLocale() {
        // اینجا می‌تونی با توجه به Session، ThreadLocal یا Header کاربر زبان رو مشخص کنی
        return Locale.getDefault();
    }
}
