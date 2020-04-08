package View;

import Model.Present;
import Model.PresentRMI;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {

    static final String rmiBindName = "present";

    public static void main(String[] args) throws Exception {
        //создание реестра расшареных объетов
        final Registry registry = LocateRegistry.getRegistry(2099);

        //получаем объект (на самом деле это proxy-объект)
        PresentRMI stub = (PresentRMI) registry.lookup(rmiBindName);


        //Вызываем удаленный метод
        stub.addItem("Alenka", 100, "Chocolate", "milk, cocao, sugar");
        stub.addItem("Sorvanec", 20,"Candy", "milk, cocao, sugar, flour");
        stub.addItem("Truffles", 300, "Candy", "milk, cocao, sugar, flour");

        String info = stub.getItemsInfo();
        System.out.println(info);

        System.out.println("Weight of present: " + stub.getWeight());

        System.out.println("Sorting present by weight.");
        stub.sortBy(null); //default by weight
        info = stub.getItemsInfo();
        System.out.println(info);

        System.out.println("Find in present items with [flour].");
        info = stub.findItem("flour");
        System.out.println(info);

        System.out.println("Delete in present item [Sorvanec].");
        stub.removeItem("Sorvanec");
        info = stub.getItemsInfo();
        System.out.println(info);
    }
}
