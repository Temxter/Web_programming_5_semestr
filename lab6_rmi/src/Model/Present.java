package Model;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collections;
import java.util.concurrent.CopyOnWriteArrayList;

public class Present implements PresentRMI, Serializable {

    private CopyOnWriteArrayList<Item> items;
    private String presentName;

    public Present() throws RemoteException {
        items = new CopyOnWriteArrayList<>();
    }

    @Override
    public double getWeight() throws RemoteException {
        double totalWeight = 0.0;
        for (Item item : items) {
            totalWeight += item.getWeight();
        }
        return totalWeight;
    }

    @Override
    public boolean removeItem(String itemName) throws RemoteException {
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

    @Override
    public void sortBy(Item.CompareByField fieldToCompare) throws RemoteException {
        Item.setCompareBy(fieldToCompare);
        Collections.sort(items);

    }

    @Override
    public String getItemsInfo() throws RemoteException {
        StringBuilder stringBuilder = new StringBuilder();
        int iter = 1;
        for (Item item : items){
            stringBuilder.append(iter + ") " + item + "\n");
            iter++;
        }
        return stringBuilder.toString();
    }

    @Override
    public void addItem(Item item) throws RemoteException {
        items.add(item);
    }

    @Override
    public void addItem(String name, double weight, String type, String consist) throws RemoteException {
        items.add(new Item(name, weight, type, consist));
    }

    @Override
    public String findItem(String someInfo) throws RemoteException {
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
