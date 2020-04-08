import client.Client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class mainClient {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your name: ");
        String username = scanner.nextLine();
        InetAddress hostIP = null;
        int port = 10001;
        try {
             hostIP = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }


        InetSocketAddress address = new InetSocketAddress(hostIP, port);

        Client client = new Client(username, address);

        Thread tRead = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true)
                    client.readMessage();
            }
        });

        tRead.start();

        String textMessage = "";
        while(textMessage.compareTo("exit") != 0){
            textMessage = scanner.nextLine();
            client.sendMessage(textMessage);
        }
        client.sendMessage(textMessage);

        tRead.interrupt();
        System.out.println("Unconnected");
    }
}
