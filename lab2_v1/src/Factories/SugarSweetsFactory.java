package Factories;

import SugarSweets.*;
import Sweet.*;

/**
 * The type Sugar sweets factory.
 */
public class SugarSweetsFactory extends  SweetFactory {


    /**
     * Get sweet sweet.
     *
     * @param type   the type
     * @param name   the name
     * @param weight the weight
     * @return the sweet
     */
    static public Sweet getSweet(SugarTypes type, String name, double weight){
        Sweet sweet = null;
        String ingredient = "sugar";
        switch (type){
            case Candy: {
                sweet = new Candy(weight, name);
                break;
            }
            case Chocolate: {
                sweet = new Chocolate(weight, name);
                break;
            }
            case Marshmallow: {
                sweet = new Marshmallow(weight, name);
                break;
            }
        }
        sweet.addItemToConsist(ingredient);
        return sweet;
    }

}
