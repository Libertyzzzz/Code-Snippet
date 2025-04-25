package netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

public class BootStrapTest {

    /**
     * @Author liberty
     * @Date 2025/4/2 12:17
     */

    public static void main(String[] args) {
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .handler(new SimpleChannelInboundHandler<ByteBuf>(){
                    @Override
                    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {
                        System.out.println("Received Data");
                    }
                });
        ChannelFuture future  = bootstrap.connect(new InetSocketAddress("www.baidu.com", 80));
        future.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                if(channelFuture.isSuccess())
                    System.out.println("Connection is established");
                else{
                    System.out.println("Connection is failed");
                    channelFuture.cause().printStackTrace();
                }

            }
        });
    }
}
