package all.Factories;

import all.FlourSweets.Cookies;
import all.FlourSweets.FlourTypes;
import all.FlourSweets.Wafers;
import all.Sweet.Sweet;

/**
 * The type FlourSweetsFactory.
 * @author Andrew
 * @version 1
 */
public class FlourSweetsFactory extends SweetFactory{

    /**
     * Gets sweet.
     *
     * @param type   the type of sweet
     * @param name   the name
     * @param weight the weight
     * @return the sweet
     */
    static public Sweet getSweet(FlourTypes type, String name, double weight, Object specific_arg) {
        Sweet sweet = null;
        String ingredient = "flour";
        switch (type) {
            case Cookies: {
                sweet = new Cookies(weight, name);
                if (specific_arg !=null && Boolean.class.isInstance(specific_arg))
                    ((Cookies) sweet).setWithGrains((boolean)specific_arg);
                sweet.addItemToConsist(ingredient);
                break;
            }
            case Wafers: {
                sweet = new Wafers(weight, name);
                if (specific_arg != null && Integer.class.isInstance(specific_arg))
                    ((Wafers) sweet).setWaferAmount((int)specific_arg);
                sweet.addItemToConsist(ingredient);
                break;
            }
        }
        return sweet;
    }
}
