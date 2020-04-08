package View;

import Model.Present;
import Model.PresentRMI;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server {

    static final String rmiBindName = "present";

    public static void main(String[] args) throws Exception {
        //создание объекта для удаленного доступа
        final Present service = new Present();

        //создание реестра расшареных объетов
        final Registry registry = LocateRegistry.createRegistry(2099);
        //создание "заглушки" – приемника удаленных вызовов
        Remote stub = UnicastRemoteObject.exportObject(service, 0);
        //регистрация "заглушки" в реесте
        registry.bind(rmiBindName, stub);

        //усыпляем главный поток, иначе программа завершится
        Thread.sleep(Integer.MAX_VALUE);

    }
}
