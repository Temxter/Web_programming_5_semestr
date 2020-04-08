package Localization;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
    static final Logger loggerLocalization = LogManager.getLogger(Localization.class);


    /**
     * Instantiates a new Localization.
     *
     * @param language the language
     */
    public Localization(String language){
        loggerLocalization.debug("Localization object create.");
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
        loggerLocalization.debug("getString with key = " + key + " : " + rb.getString(key));
        return  rb.getString(key);
    }
}
