package netty;


import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;


public class DiscardClientHandler extends ChannelInboundHandlerAdapter {

    /**
     * @Author liberty
     * @Date 2025/3/19 15:29
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // 连接建立后发送数据
        String message = "Hello from DiscardClient!\n";
        ByteBuf buffer = Unpooled.copiedBuffer(message.getBytes());
        ctx.writeAndFlush(buffer); // 发送数据到服务端
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 如果服务端有响应，这里处理（本例中服务端丢弃数据，无响应）
        // 接收服务端回复
        ByteBuf in = (ByteBuf) msg;
        try {
            System.out.println("Client received: " + in.toString(CharsetUtil.UTF_8));
        } finally {
            in.release();
        }
        ctx.close(); // 收到回复后关闭连接（可选）
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

}
