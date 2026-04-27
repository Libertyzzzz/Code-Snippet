package netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class DiscardClient {

    private final String host;
    private final int port;

    public DiscardClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void run() throws Exception {
        EventLoopGroup group = new NioEventLoopGroup(); // (1)
        try {
            Bootstrap b = new Bootstrap(); // (2)
            b.group(group)                 // (3)
                    .channel(NioSocketChannel.class) // (4)
                    .handler(new ChannelInitializer<SocketChannel>() { // (5)
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new DiscardClientHandler());
                        }
                    });

            // 连接到服务端
            ChannelFuture f = b.connect(host, port).sync(); // (6)

            // 等待连接关闭
            f.channel().closeFuture().sync(); // (7)
        } finally {
            group.shutdownGracefully(); // (8)
        }
    }

    public static void main(String[] args) throws Exception {
        String host = "localhost";
        int port = 8080;
        if (args.length > 1) {
            host = args[0];
            port = Integer.parseInt(args[1]);
        }
        new DiscardClient(host, port).run();

    }
}

