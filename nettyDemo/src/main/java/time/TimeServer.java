package time;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.timeout.*;
import io.netty.util.concurrent.DefaultThreadFactory;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author parry
 * @date 2020/02/18
 */
public class TimeServer {
    
    private static String separator = System.getProperty("line.separator");
    
    /**
     * reactor三种线程的使用样例
     */
    public void example() {
        ServerBootstrap bootstrap = new ServerBootstrap();
        // 单线程模型 (单Reactor单线程)
        // 连接请求、IO处理请求全部由这一个线程承担
        EventLoopGroup single = new NioEventLoopGroup(1, new DefaultThreadFactory("single"));
        bootstrap.group(single, single);
        // 多线程模型 (单Reactor多线程)
        // 连接请求由线程池中一个线程承担，然后IO处理由其他线程承担
        EventLoopGroup multi = new NioEventLoopGroup(10, new DefaultThreadFactory("single"));
        bootstrap.group(multi, multi);
        // 主从reactor模型 (多Reactor多线程) 一主多从 如果只监听一个端口的话 主Reactor只需要一个线程即可
        // mainReactor用于接收连接 然后将IO事件分发给subReactor
        EventLoopGroup mainReactor = new NioEventLoopGroup(1, new DefaultThreadFactory("parent"));
        EventLoopGroup subReactor = new NioEventLoopGroup(10, new DefaultThreadFactory("child"));
        bootstrap.group(mainReactor, subReactor);
        
    }
    
    public void bind(int port) throws Exception {
//        线程组 一个用于接受客户端连接 一个用于IO操作
//        如果只监听一个端口则只需要一个线程即可，因为一个NioServerSocketChannel只能够与一个NioEventLoop绑定，该channel的所有操作均由绑定的NioEventLoop进行
//        Channel的注册、绑定操作都由该线程承担
        EventLoopGroup parentGroup = new NioEventLoopGroup(1, new DefaultThreadFactory("parent"));
//        childGroup是IO操作用的线程池，注册到boss线程的Channel监听到连接事件后会将后续处理分发到该线程池
        EventLoopGroup childGroup = new NioEventLoopGroup(10, new DefaultThreadFactory("child"));
//        netty启动辅助类
        ServerBootstrap bootstrap = new ServerBootstrap();
        try {
//            绑定线程组
            bootstrap.group(parentGroup, childGroup)
//                设置使用的channel
                .channel(NioServerSocketChannel.class)
//                设置参数
                .option(ChannelOption.SO_BACKLOG, 1024)
//                绑定事件处理类
                .childHandler(new ChildChannelHandler());
//            绑定端口并且同步等待操作完成
            ChannelFuture channelFuture = bootstrap.bind(port);
//            等待服务端链路关闭之后main函数才退出
            channelFuture.channel().closeFuture().sync();
        } finally {
//            优雅关闭线程组,释放资源
            parentGroup.shutdownGracefully();
            childGroup.shutdownGracefully();
        }
    }
    
    private static class ChildChannelHandler extends ChannelInitializer<SocketChannel> {
        
        @Override
        protected void initChannel(SocketChannel socketChannel) throws Exception {
            System.out.println("ChildChannelHandler init");
            socketChannel.pipeline()
//                除了该handler还有ReadTimeoutHandler与WriteTimeoutHandler
                .addLast(new IdleStateHandler(0, 0, 5))
//                按照行分隔符来区分包数据
                .addLast(new LineBasedFrameDecoder(1024))
//                将接收到的数据转换成字符串
                .addLast(new StringDecoder())
                .addLast(new TimeServerHandler());
        }
    }
    
    private static class TimeServerHandler extends ChannelHandlerAdapter {
        
        private int count = 0;
        
        private int lossConnectCount = 0;
        
        private final String PING = "ping";
    
        public TimeServerHandler() {
            System.out.println("timeServerHandler init");
        }
    
        /**
         * 捕获异常
         */
        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            ctx.close();
        }
        
        /**
         * 从channel读数据并响应
         */
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            count++;
            if (PING.equals(msg)) {
                System.out.println("收到心跳");
                super.channelRead(ctx, msg);
            }
            lossConnectCount = 0;
            String body;
            if (msg instanceof ByteBuf) {
                ByteBuf byteBuffer = (ByteBuf) msg;
                byte[] req = new byte[byteBuffer.readableBytes()];
                byteBuffer.readBytes(req);
                body = new String(req, StandardCharsets.UTF_8);
            } else {
                body = msg.toString();
            }
            System.out.println(this.toString() + "receive msg : " + body + ", count = " + count);
            ByteBuf resp = Unpooled.copiedBuffer((new Date().toString() + separator).getBytes());
            ctx.write(resp);
        }
        
        /**
         * channel数据读完
         */
        @Override
        public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
            ctx.flush();
        }
        
        @Override
        public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
            if (evt instanceof IdleStateEvent) {
                IdleStateEvent event = (IdleStateEvent) evt;
                if (event.state() == IdleState.ALL_IDLE) {
                    lossConnectCount++;
                    System.out.println("第" + lossConnectCount + "次读写超时");
                    if (lossConnectCount > 2) {
                        System.out.println("关闭该连接");
                        ctx.channel().close();
                    }
                } else {
                    super.userEventTriggered(ctx, evt);
                }
            }
        }
    }
    
    public static void main(String[] args) throws Exception {
        int port = 8080;
        new TimeServer().bind(port);
    }
}
