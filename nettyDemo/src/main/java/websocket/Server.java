package websocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.util.CharsetUtil;

import java.util.Date;

/**
 * @author parry
 * @date 2020/05/14
 */
public class Server {
    
    public static void main(String[] args) throws Exception {
        NioEventLoopGroup parentEventLoopGroup = new NioEventLoopGroup();
        NioEventLoopGroup childEventLoopGroup = new NioEventLoopGroup();
        ServerBootstrap serverBootstrap = new ServerBootstrap()
            .group(parentEventLoopGroup, childEventLoopGroup)
            .channel(NioServerSocketChannel.class)
            .option(ChannelOption.SO_BACKLOG, 1024)
            .childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast("http-codec", new HttpServerCodec())
                        .addLast("http-aggregator", new HttpObjectAggregator(65536))
                        .addLast("http-chunked", new ChunkedWriteHandler())
                        .addLast("fileServerHandler", new WebSocketServerHandler());
                }
            });
        serverBootstrap.bind(8080).channel().closeFuture().sync();
        parentEventLoopGroup.shutdownGracefully();
        childEventLoopGroup.shutdownGracefully();
    }
    
    private static class WebSocketServerHandler extends SimpleChannelInboundHandler<Object> {
    
        private WebSocketServerHandshaker handShaker;
        
        @Override
        protected void messageReceived(ChannelHandlerContext ctx, Object msg) throws Exception {
            if (msg instanceof FullHttpRequest) {
                handleHttpRequest(ctx, (FullHttpRequest) msg);
            }else if (msg instanceof WebSocketFrame) {
                handleWebSocketFrame(ctx, (WebSocketFrame) msg);
            }
            
        }
    
        @Override
        public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
            ctx.flush();
        }
        
        private void handleHttpRequest(ChannelHandlerContext ctx, FullHttpRequest request) {
            if (!request.getDecoderResult().isSuccess() || !"websocket".equals(request.headers().get("Upgrade"))) {
                System.out.println("fail");
                return;
            }
    
            WebSocketServerHandshakerFactory webSocketServerHandshakerFactory = new WebSocketServerHandshakerFactory("ws://127.0.0.1:8080/websocket", null, false);
            WebSocketServerHandshaker handShaker = webSocketServerHandshakerFactory.newHandshaker(request);
            if (handShaker == null) {
                WebSocketServerHandshakerFactory.sendUnsupportedWebSocketVersionResponse(ctx.channel());
            }else {
                handShaker.handshake(ctx.channel(), request);
            }
        }
        
        private void handleWebSocketFrame(ChannelHandlerContext ctx, WebSocketFrame frame) {
            if (frame instanceof CloseWebSocketFrame) {
                handShaker.close(ctx.channel(), ((CloseWebSocketFrame) frame).retain());
                return;
            }
            if (frame instanceof PingWebSocketFrame) {
                ctx.channel().writeAndFlush(new PongWebSocketFrame(frame.content().retain()));
                return;
            }
            if (!(frame instanceof TextWebSocketFrame)) {
                throw new UnsupportedOperationException("不知道文本消息");
            }
            String request = ((TextWebSocketFrame) frame).text();
            System.out.println("收到消息" + request);
            ctx.channel().write(new TextWebSocketFrame(request + ", now time:" + new Date().toString()));
        }
        
    }
}
