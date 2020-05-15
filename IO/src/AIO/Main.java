package AIO;

import java.io.File;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.util.concurrent.Future;

/**
 * @author parry
 * @date 2020/01/22
 */
public class Main {
    private static Charset charset = Charset.forName("US-ASCII");
    private static CharsetEncoder encoder = charset.newEncoder();
    
    public static void main(String[] args) throws Exception {
        read();
    }
    
    private static void read() throws Exception {
        AsynchronousFileChannel channel = AsynchronousFileChannel.open(new File("file.txt").toPath());
        ByteBuffer byteBuffer = ByteBuffer.allocate(128);
        Future<Integer> read = channel.read(byteBuffer, 0);
        Integer readByte = read.get();
        Integer position = 0;
        StringBuilder sb = new StringBuilder();
        while (readByte != null && readByte != -1) {
            position += readByte;
            //将缓冲区从写模式切换成读模式
            byteBuffer.flip();
            byte[] bytes = new byte[byteBuffer.limit()];
            byteBuffer.get(bytes);
            System.out.println(new String(bytes));
            sb.append(new String(bytes));
            byteBuffer.compact();
            readByte = channel.read(byteBuffer, position).get();
        }
        System.out.println("----------------------");
        System.out.println(sb.toString());
        channel.close();
    }
}
