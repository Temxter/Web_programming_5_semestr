package server;

import model.Message;
import model.User;

import java.io.*;
import java.net.Socket;

public class HandlerMessages implements Runnable {
    private Socket clientSocket;
    private ObjectInputStream objectInputStream;
    private BroadcastMessage broadcastMessage;

    public HandlerMessages(Socket clientSocket, BroadcastMessage broadcastMessage) {
        this.clientSocket = clientSocket;
        this.broadcastMessage = broadcastMessage;
        new Thread(this).start();
    }

    private boolean registerClient( ){
        writeUserToFile();
        return true;
    }

    private void writeUserToFile(){
        Message message = receive();
        User user = message.getUser();
        try {
            FileWriter fw = new FileWriter("userdata.txt", true);
            fw.write(user.getName() + " " + user.getPassword() + "\n");
            fw.close();
            System.out.println("Written to file user: " + user.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Message receive(){
        Message inputMessage = null;
        try {
            inputMessage = (Message)objectInputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(inputMessage);
        return inputMessage;
    }

    private void printMessage(Message message){
        System.out.println(message);
    }

    private void init(){
        try {
            objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        writeUserToFile();
    }

    @Override
    public void run() {
        init();
        while(clientSocket.isConnected()){
            Message inputMessage = receive();
            broadcastMessage.broadCast(clientSocket, inputMessage);
        }
        broadcastMessage.removeSocket(clientSocket);
    }
}
