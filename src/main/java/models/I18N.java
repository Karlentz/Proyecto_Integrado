package models;

import java.util.Locale;
import java.util.ResourceBundle;

public class I18N {
    public static ResourceBundle bundle;
    static Locale local;

    public static void setLocale(Locale locale) {
        local = locale;
        Locale.setDefault(locale);
    }

    public static Locale getLocale() {
        return local;
    }
}
