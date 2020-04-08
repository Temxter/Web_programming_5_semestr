package server;

import model.Message;
import org.apache.commons.lang.SerializationUtils;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

public class Server {
    private ServerSocketChannel serverSocketChannel;
    private Selector selector;
    private final int BUFFER_SIZE = 1024*100;
    private ArrayList<SocketChannel> clientSocketChannels;

    private static void logger(String msg){
        System.out.println(msg);
    }

    public Server(){
        clientSocketChannels = new ArrayList<>();
        try {
            InetAddress hostIP = InetAddress.getLocalHost();
            int port = 10001;
            selector = Selector.open();
            InetSocketAddress address = new InetSocketAddress(hostIP, port);
            serverSocketChannel = ServerSocketChannel.open();

            ServerSocket serverSocket = serverSocketChannel.socket();
            serverSocket.bind(address);

            serverSocketChannel.configureBlocking(false);
            int ops = serverSocketChannel.validOps();
            serverSocketChannel.register(selector, ops);
            logger("Server started!");
            logger(String.format("Server address: %s:%d", hostIP.toString(), port));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run(){
        while(true){
            try {
                selector.select();

                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> keyIterator = selectionKeys.iterator();

                while (keyIterator.hasNext()){
                    SelectionKey key = keyIterator.next();

                    if (key.isAcceptable()){
                        acceptAction();
                    }
                    else if (key.isReadable()){
                        //logger("[key.isReadable()] == true");
                        readAction(key);
                    }

                    keyIterator.remove();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void acceptAction(){
        try {

            SocketChannel client = serverSocketChannel.accept();
            client.configureBlocking(false);
            client.register(selector, SelectionKey.OP_READ);

            logger(String.format("Client connected"));

            clientSocketChannels.add(client);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readAction(SelectionKey key){
        SocketChannel clientSender = (SocketChannel)key.channel();
        ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
        try {

            clientSender.read(buffer);
            Message message = (Message) SerializationUtils.deserialize(buffer.array());
            //logger("Deserialization");

            System.out.println(message);

            if (message.getText().equalsIgnoreCase("!exit")) {
                clientSocketChannels.remove(clientSender);
                clientSender.close();
            }
            else if (message.getText().equalsIgnoreCase("registration")) {
                logger("Name of new user: " + message.getUser().getName());
            }
            else {
                buffer.flip();
                for (SocketChannel client : clientSocketChannels) {
                    if (client == clientSender)
                        continue;
                    client.write(buffer);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
