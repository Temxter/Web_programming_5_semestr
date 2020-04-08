package Server;

import io.netty.channel.*;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;


public class ChatServerHandler extends SimpleChannelInboundHandler<String> {

    static final ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String message) throws Exception {
        Channel incoming = ctx.channel();

        serverBroadcastClientAction(incoming, incoming.remoteAddress().toString(), message, true);
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        Channel incoming = ctx.channel();

        serverBroadcastClientAction(incoming, "SERVER", "[" + incoming.remoteAddress() + "] " + "has connected.", false);
        channels.add(incoming);
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        Channel incoming = ctx.channel();
        serverBroadcastClientAction(incoming, "SERVER","[" + incoming.remoteAddress() + "] " + "has disconnected.", false);
        channels.remove(incoming);
        incoming.close();
        ctx.close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ChatServerLogger.console("Exception: " + cause.getMessage());
    }

    private void serverBroadcastClientAction(Channel incoming, String from, String message, boolean justMessage) {

        String finallyMessage = "[" + from + "]: " + message + "\n";

        if (justMessage == false)
            ChatServerLogger.connections(finallyMessage);

        ChatServerLogger.console(finallyMessage);

        for (Channel channel : channels){
            if (channel != incoming){
                channel.writeAndFlush(finallyMessage);
            }
        }
    }


}
