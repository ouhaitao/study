package NIO;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.AbstractSelector;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

/**
 * @author parry
 * @date 2020/05/14
 */
public class Client {
    
    public static void main(String[] args) throws Exception {
        ByteBuffer readBuffer = ByteBuffer.allocateDirect(1024);
        ByteBuffer writeBuffer = StandardCharsets.UTF_8.encode("client");
        // 开启客户端channel并连接服务器
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress(8080));
        // 设置为非阻塞，SocketChannel
        SelectableChannel selectableChannel = socketChannel.configureBlocking(false);
        // 开启selector
        AbstractSelector selector = selectableChannel.provider().openSelector();
        // 将serverSocketChannel注册到selector上
        if (socketChannel.isConnected()) {
            selectableChannel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
        }else {
            selectableChannel.register(selector, SelectionKey.OP_CONNECT);
        }
        
        // 阻塞操作，直到有就绪事件才返回
        while (selector.select() > 0) {
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                iterator.remove();
                SocketChannel clientChannel = (SocketChannel) selectionKey.channel();
                
                if (selectionKey.isConnectable()) {
                    System.out.println("连接事件");
                    clientChannel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
                }
                if (selectionKey.isReadable()) {
                    System.out.println("读事件");
                    readBuffer.clear();
                    clientChannel.read(readBuffer);
                    readBuffer.flip();
                    String string = StandardCharsets.UTF_8.decode(readBuffer).toString();
                    System.out.println("收到数据" + string);
                    clientChannel.register(selector, SelectionKey.OP_WRITE);
                }
                // 事实上只要成功连接上且网络缓冲区有剩余空间可写，则写事件一直就绪，所以并不需要监听该操作，在需要写入的时候直接写就行了，这里仅做试验
                if (selectionKey.isWritable()) {
                    System.out.println("写事件");
                    clientChannel.write(writeBuffer.duplicate());
                    clientChannel.register(selector, SelectionKey.OP_READ);
                }
                if (selectionKey.isAcceptable()) {
                    System.out.println("客户端不关心");
                }
            }
        }
        
    }
}
