import Factories.*;
import FlourSweets.FlourTypes;
import SugarSweets.Candy;
import SugarSweets.SugarTypes;
import Sweet.*;

import java.util.ArrayList;
import java.util.Collections;

class Main {

    public static void main(String[] args) {
        SugarSweetsFactory ssf = (SugarSweetsFactory)SweetFactory.getFactory(FactoryType.SugarSweatsFactory);
        FlourSweetsFactory fsf = (FlourSweetsFactory)SweetFactory.getFactory(FactoryType.FlourSweatsFactory);
        ArrayList<Sweet> sweets = new ArrayList<Sweet>();
        Sweet sorvanec = ssf.getSweet(SugarTypes.Candy, "Sorvanec", 20.0);

        Sweet blackSea = fsf.getSweet(FlourTypes.Wafers, "BlackSea", 200.0);

        Sweet alenka = ssf.getSweet(SugarTypes.Chocolate, "Alenka", 100.0);

        sweets.add(sorvanec);
        sweets.add(blackSea);
        sweets.add(alenka);


        for (Sweet sweet : sweets){
            System.out.println(sweet + "\n");
        }

        Collections.sort(sweets);

        System.out.println("Sorted gift by weight:");

        for (Sweet sweet : sweets){
            System.out.println(sweet + "\n");
        }




    }
}
