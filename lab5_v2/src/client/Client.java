package client;

import model.Message;
import model.User;

import org.apache.commons.lang.SerializationUtils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Set;

public class Client {
    private User user;
    private SocketChannel socketChannel;
    private int messageCapacity = 1024*100;
    private Selector selector;

    private static void logger(String msg){
        System.out.println(msg);
    }


    public Client(String username, InetSocketAddress address) {
        user = new User(username);
        try {

            selector = Selector.open();
            socketChannel = SocketChannel.open(address);
            socketChannel.configureBlocking(false);
            socketChannel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);

            logger("Connected");
        } catch (IOException e) {
            e.printStackTrace();
        }
        registration();
    }


    public void sendMessage(String text){
        try {
            selector.select();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Set<SelectionKey> selectionKeys = selector.selectedKeys();
        for (SelectionKey key : selectionKeys) {
            if (key.isWritable()) {

                Message message = new Message(text, user);
                byte[] data = SerializationUtils.serialize(message);
                ByteBuffer byteBuffer = ByteBuffer.allocate(messageCapacity);
                byteBuffer.put(data);
                byteBuffer.flip();
                //logger("Client send message");
                try {
                    socketChannel.write(byteBuffer);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    public void readMessage(){
        try {
            selector.select();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Set<SelectionKey> selectionKeys = selector.selectedKeys();
        for (SelectionKey key : selectionKeys){
            if (key.isReadable()){
                try {

                    //logger("Client read message");
                    ByteBuffer byteBuffer = ByteBuffer.allocate(messageCapacity);
                    socketChannel.read(byteBuffer);
                    Message message = (Message)SerializationUtils.deserialize(byteBuffer.array());
                    System.out.println(message);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void registration(){
        sendMessage("registration");
    }

}
