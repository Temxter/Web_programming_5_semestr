package Model;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface PresentRMI extends Remote {
    double getWeight() throws RemoteException;
    boolean removeItem(String itemName) throws RemoteException;
    void sortBy(Item.CompareByField fieldToCompare) throws RemoteException;
    String getItemsInfo() throws RemoteException;
    void addItem(Item item) throws RemoteException;
    void addItem(String name, double weight, String type, String consist) throws RemoteException;
    String findItem(String someInfo) throws RemoteException;
}
