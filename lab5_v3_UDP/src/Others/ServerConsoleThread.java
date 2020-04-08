package Others;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.socket.DatagramPacket;
import io.netty.util.CharsetUtil;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ServerConsoleThread implements Runnable {

    private static ChannelGroup channels;
    private static String SHUTDOWN = "$exit";
    private static String BROADCAST_LOG = "$blog";
    private int positionInLog = 0;
    private BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    public ServerConsoleThread(ChannelGroup channels) {
        this.channels = channels;
        new Thread(this);
    }

    @Override
    public void run() {
        String message = "";
        while (message.equalsIgnoreCase(SHUTDOWN) == false) {
            message = readString();
            if (message.equalsIgnoreCase(BROADCAST_LOG) == true) {
                byte[] readLogFile = readLogFile();
                broadcasting(readLogFile);
            }
        }
    }

    private String readString(){
        String str = "";
        try {
            str = in.readLine();
        } catch (IOException ex) {
            System.err.println("[Others.ServerConsoleThread] Reading string error: " + ex.getMessage());
        }
        return str;
    }

    private void broadcasting(byte [] message){
        for (Channel channel : channels) {
            InetSocketAddress address = (InetSocketAddress)channel.remoteAddress();
            DatagramPacket packet = new DatagramPacket(Unpooled.copiedBuffer(message), address);
            channel.writeAndFlush(packet);
        }
        //channels.writeAndFlush(message.toCharArray());
    }

    private byte[] readLogFile(){
        byte [] bytes = null;
        try {
            bytes = Files.readAllBytes(Paths.get("connections.log"));
        } catch (IOException ex) {
            System.err.println("[Others.ServerConsoleThread] reading string error: " + ex.getMessage());
        }
        return bytes;
    }
}
