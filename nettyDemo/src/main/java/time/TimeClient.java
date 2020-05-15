package time;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author parry
 * @date 2020/02/18
 */
public class TimeClient {
    
    private static String separator = System.getProperty("line.separator");
    private static volatile boolean open = true;
    
    public void connect(int port, String host) throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group).channel(NioSocketChannel.class)
//                是否启用nagle算法
                .option(ChannelOption.TCP_NODELAY, true)
//                连接超时设置
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 3000)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline()
                            .addLast(new IdleStateHandler(0, 0, 4))
                            .addLast(new LineBasedFrameDecoder(1024))
                            .addLast(new StringDecoder())
                            .addLast(new TimeClientHandler(bootstrap));
                    }
                });
                ChannelFuture channelFuture = bootstrap.connect(host, port);
                channelFuture.channel().closeFuture().sync();
                while (open);
        } finally {
            group.shutdownGracefully();
        }
    }
    
    private static class TimeClientHandler extends ChannelHandlerAdapter {
        
        private final ByteBuf firstMessage = Unpooled.copiedBuffer(("hello" + separator).getBytes());
        
        private final ByteBuf PING = Unpooled.copiedBuffer(("ping" + separator).getBytes());
    
        private final Bootstrap bootstrap;
    
        public TimeClientHandler(Bootstrap bootstrap) {
            this.bootstrap = bootstrap;
        }
    
        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            ctx.close();
        }
        
        /**
         * channel激活时调用
         */
        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            System.out.println(Thread.currentThread().getName() + ":channelActive");
//            for (int i = 0; i < 100; i++) {
//                一个ByteBuf只能使用一次
                ctx.writeAndFlush(Unpooled.copiedBuffer(firstMessage));
//            }
        }
        
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            String body;
            if (msg instanceof ByteBuf) {
                ByteBuf byteBuf = (ByteBuf) msg;
                byte[] req = new byte[byteBuf.readableBytes()];
                byteBuf.readBytes(req);
                body = new String(req, StandardCharsets.UTF_8);
            } else {
                body = msg.toString();
            }
            System.out.println("receive msg: " + body);
        }
        
        @Override
        public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
            if (evt instanceof IdleStateEvent) {
                IdleStateEvent event = (IdleStateEvent) evt;
                if (event.state() == IdleState.ALL_IDLE) {
                    System.out.println("读写超时,重试");
                    if (ctx.channel().isOpen()) {
                        System.out.println("发送心跳");
                        ctx.writeAndFlush(Unpooled.copiedBuffer(PING));
                    } else {
                        System.out.println("关闭channel");
                        ctx.channel().close();
                    }
                } else {
                    super.userEventTriggered(ctx, evt);
                }
            }
        }
    
        @Override
        public void channelInactive(ChannelHandlerContext ctx) throws Exception {
            System.out.println("断线重连");
            ChannelFuture connectFuture = bootstrap.connect("127.0.0.1", 8080).sync();
            System.out.println("11111");
            if (connectFuture.isDone()) {
                if (connectFuture.isSuccess()) {
                    System.out.println("重连成功");
                }else {
                    System.out.println("重连失败");
                    connectFuture.cause().printStackTrace();
                    open = false;
                }
            }else {
                connectFuture.addListener(future -> {
                   if (future.isSuccess()) {
                       System.out.println("重连成功");
                   }else {
                       System.out.println("重连失败");
                       open = false;
                   }
                });
            }
        }
    }
    
    public static void main(String[] args) throws Exception {
        int port = 8080;
        TimeClient timeClient = new TimeClient();
        timeClient.connect(port, "127.0.0.1");
        
    }
}
