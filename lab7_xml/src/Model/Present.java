package Model;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collections;

public class Present {

    private ArrayList<Item> items;
    private String presentName;

    public Present() {
        items = new ArrayList<>();
    }

    public Present(ArrayList<Item> items) {
        this.items = items;
    }

    public double getWeight() {
        double totalWeight = 0.0;
        for (Item item : items) {
            totalWeight += item.getWeight();
        }
        return totalWeight;
    }

    public boolean removeItem(String itemName) {
        boolean isDeleted = false;
        for (Item item : items) {
            if (item.getName().compareTo(itemName) == 0){
                items.remove(item);
                isDeleted = true;
                break;
            }
        }
        return isDeleted;
    }

    public void sortBy(Item.CompareByField fieldToCompare) {
        Item.setCompareBy(fieldToCompare);
        Collections.sort(items);

    }

    public String getItemsInfo() {
        StringBuilder stringBuilder = new StringBuilder();
        int iter = 1;
        for (Item item : items){
            stringBuilder.append(iter + ") " + item + "\n");
            iter++;
        }
        return stringBuilder.toString();
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void addItem(String name, double weight, String type, String consist) {
        items.add(new Item(name, weight, type, consist));
    }

    public String findItem(String someInfo) {
        StringBuilder stringBuilder = new StringBuilder();
        int iter = 1;
        for (Item item : items){
            if(item.toString().toLowerCase().contains(someInfo.toLowerCase())) {
                stringBuilder.append(iter + ") " + item + "\n");
                iter++;
            }
        }
        return stringBuilder.toString();
    }
}
