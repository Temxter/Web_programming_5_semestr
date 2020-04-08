package Factories;

import FlourSweets.*;
import Sweet.*;

/**
 * The type Flour sweets factory.
 */
public class FlourSweetsFactory extends SweetFactory{

    /**
     * Gets sweet.
     *
     * @param type   the type
     * @param name   the name
     * @param weight the weight
     * @return the sweet
     */
    static public Sweet getSweet(FlourTypes type, String name, double weight) {
        Sweet sweet = null;
        String ingredient = "flour";
        switch (type) {
            case Cookies: {
                sweet = new Cookies(weight, name);
                sweet.addItemToConsist(ingredient);
                break;
            }
            case Wafers: {
                sweet = new Wafers(weight, name);
                sweet.addItemToConsist(ingredient);
                break;
            }
        }
        return sweet;
    }
}
