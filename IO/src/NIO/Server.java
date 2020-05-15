package NIO;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.channels.spi.AbstractSelector;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

/**
 * @author parry
 * @date 2020/05/14
 */
public class Server {
    
    public static void main(String[] args) throws Exception {
        ByteBuffer readBuffer = ByteBuffer.allocateDirect(1024);
        ByteBuffer writeBuffer = StandardCharsets.UTF_8.encode("server");
        // 开启服务端channel
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open().bind(new InetSocketAddress(8080));
        // 设置为非阻塞，这里返回的是serverSocketChannel
        SelectableChannel selectableChannel = serverSocketChannel.configureBlocking(false);
        // 开启selector
        AbstractSelector selector = selectableChannel.provider().openSelector();
        // 将serverSocketChannel注册到selector上
        selectableChannel.register(selector, SelectionKey.OP_ACCEPT);
        
        // 阻塞操作，直到有就绪事件才返回
        while (selector.select() > 0) {
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                iterator.remove();
                
                if (selectionKey.isAcceptable()) {
                    ServerSocketChannel serverChannel = (ServerSocketChannel) selectionKey.channel();
                    System.out.println("连接事件");
                    // 建立客户端channel
                    SocketChannel clientChannel = serverChannel.accept();
                    if (clientChannel == null) {
                        continue;
                    }
                    // 将客户端channel注册到selector上
                    SelectableChannel clientSelectableChannel = clientChannel.configureBlocking(false);
                    clientSelectableChannel.register(selector, SelectionKey.OP_READ);
                }
                if (selectionKey.isReadable()) {
                    System.out.println("读事件");
                    SocketChannel clientChannel = (SocketChannel) selectionKey.channel();
                    readBuffer.clear();
                    clientChannel.read(readBuffer);
                    readBuffer.flip();
                    String string = StandardCharsets.UTF_8.decode(readBuffer).toString();
                    System.out.println("收到数据" + string);
                    clientChannel.write(writeBuffer.duplicate());
                }
                if (selectionKey.isWritable() || selectionKey.isConnectable()) {
                    System.out.println("服务端不关心");
                }
            }
        }
        
    }
}
