import client.Client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class mainClient {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your name: ");
        String username = scanner.nextLine();
        Socket socket = null;
        try {
            socket = new Socket("localhost", 10001);
        } catch (UnknownHostException e) {
            e.printStackTrace();
            System.exit(-1);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }

        Client client = new Client(username, socket);

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

        tRead.interrupt();
        System.out.println("Unconnected");
    }
}
