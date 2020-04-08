package server;

import model.Message;
import model.User;

import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;

public class BroadcastMessage {

    static private BroadcastMessage broadcastMessage;
    static private boolean singleton = false;
    private HashMap<Socket, ObjectOutputStream> clientSockets;

    private BroadcastMessage(){
        clientSockets = new HashMap<>();
        singleton = true;
        broadcastMessage = this;
    }

    static public BroadcastMessage createBroadcast(){
        if (!singleton)
            return new BroadcastMessage();
        else
            return broadcastMessage;
    }

    void addSocket(Socket client){
        ObjectOutputStream objectOutputStream = null;
        try {

            objectOutputStream = new ObjectOutputStream(client.getOutputStream());
            clientSockets.put(client, objectOutputStream);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void removeSocket(Socket client){
        ObjectOutputStream oos = clientSockets.remove(client);
        assert oos != null: "removeSocket error";
    }

    private void write(ObjectOutputStream oos, Message message){
        try {
            oos.writeObject(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void broadCast(Socket fromClient, Message message){
        for (Socket client : clientSockets.keySet()) {
            if (client.equals(fromClient))
                continue;
            ObjectOutputStream oos = clientSockets.get(client);
            write(oos, message);
        }
    }
}
