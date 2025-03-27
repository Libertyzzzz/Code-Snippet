package netty;

import io.lettuce.core.StrAlgoArgs;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

public class DiscardServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * @Author liberty
     * @Date 2025/3/19 13:07
     */

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf in = (ByteBuf) msg;
        try {
            System.out.println("Received: " );
            String receivedData = in.toString(CharsetUtil.UTF_8);
            System.out.println(receivedData);
            System.out.println("--------------");
            // 回复客户端：将收到的消息回显回去
            ByteBuf response = Unpooled.copiedBuffer("Server reply: " + receivedData, CharsetUtil.UTF_8);
            ctx.writeAndFlush(response); // 发送回复

        }finally {
            in.release();
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
