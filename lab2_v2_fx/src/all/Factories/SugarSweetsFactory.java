package all.Factories;

import all.SugarSweets.Candy;
import all.SugarSweets.Chocolate;
import all.SugarSweets.Marshmallow;
import all.SugarSweets.SugarTypes;
import all.Sweet.Sweet;

/**
 * The type SugarSweetsFactory.
 * @author Andrew
 * @version 1
 */
public class SugarSweetsFactory extends  SweetFactory {


    /**
     * Get Sugarsweet.
     *
     * @param type   the type
     * @param name   the name
     * @param weight the weight
     * @return the sweet
     */
    static public Sweet getSweet(SugarTypes type, String name, double weight, Object specific_arg){
        Sweet sweet = null;
        String ingredient = "sugar";
        switch (type){
            case Candy: {
                sweet = new Candy(weight, name);
                if (specific_arg != null && Candy.CandyForm.class.isInstance(specific_arg))
                    ((Candy) sweet).setCandyForm(((Candy.CandyForm)specific_arg));
                break;
            }
            case Chocolate: {
                sweet = new Chocolate(weight, name);
                if (specific_arg != null && Double.class.isInstance(specific_arg))
                   ((Chocolate) sweet).setCacaoPercent((double)specific_arg);
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
