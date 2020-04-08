package Localization;

import java.io.FileInputStream;
import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * The type Localization.
 */
public class Localization {
    private ResourceBundle rb;
    private Locale locale;


    /**
     * Instantiates a new Localization.
     *
     * @param language the language
     */
    public Localization(String language){
        locale = new Locale(language, Locale.getDefault().getCountry());
        rb = ResourceBundle.getBundle("Localization/main", locale);
    }

    /**
     * Get string string.
     *
     * @param key the key
     * @return the string
     */
    public String getString(String key){
        return  rb.getString(key);
    }
}
