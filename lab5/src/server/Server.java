package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable{

    private Thread serverThread;

    private ServerSocket serverSocket;
    private BroadcastMessage broadcastMessage;

    public Server(int port) {
        broadcastMessage = BroadcastMessage.createBroadcast();
        try {
            this.serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                Socket clientSocket = serverSocket.accept();
                broadcastMessage.addSocket(clientSocket);
                new HandlerMessages(clientSocket, broadcastMessage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void startServer(){
        serverThread = new Thread(this);
        serverThread.start();
    }

    public void interruptServer(){
        serverThread.interrupt();
    }

    public void join(){
        try {
            serverThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
