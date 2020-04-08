import io.netty.channel.*;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;


public class ChatServerHandler extends SimpleChannelInboundHandler<String> {

    private void logger(String s){
        System.out.println(s);
    }

    static final ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String message) throws Exception {
        Channel incoming = ctx.channel();

        serverBroadcastClientAction(incoming, incoming.remoteAddress().toString(), message);
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        Channel incoming = ctx.channel();

        serverBroadcastClientAction(incoming, "SERVER", "[" + incoming.remoteAddress() + "]: " + "has connected.");
        channels.add(incoming);
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        Channel incoming = ctx.channel();
        serverBroadcastClientAction(incoming, "SERVER","[" + incoming.remoteAddress() + "]: " + "has disconnected.");
        channels.remove(incoming);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger("Exception: " + cause.getMessage());
    }

    private void serverBroadcastClientAction(Channel incoming, String from, String message) {

        logger("[" + from + "]: " + incoming.remoteAddress() + " " + message);

        for (Channel channel : channels){
            if (channel != incoming){
                channel.writeAndFlush("[" + from + "]: " + " " + message + "\n");
            }
        }
    }


}
