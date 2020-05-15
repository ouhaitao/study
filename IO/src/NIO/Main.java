package NIO;


import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class Main {
    
    public static void main(String[] args) {
        read();
    }
    
    private static void read() {
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile("file.txt", "rw");
            FileChannel fileChannel = randomAccessFile.getChannel();
            //申请缓冲区空间
            ByteBuffer byteBuffer = ByteBuffer.allocate(128);
            //将数据从channel中读取到buffer中
            int readByte = fileChannel.read(byteBuffer);
            StringBuilder sb = new StringBuilder();
            while (readByte != -1) {
                //将缓冲区从写模式切换成读模式
                byteBuffer.flip();
                //读模式下,limit即为buffer中的数据总数
                byte[] bytes = new byte[byteBuffer.limit()];
                byteBuffer.get(bytes);
                sb.append(new String(bytes));
                byteBuffer.compact();
                readByte = fileChannel.read(byteBuffer);
            }
            fileChannel.close();
            System.out.println(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static void write() {
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile("file.txt", "rw");
            FileChannel fileChannel = randomAccessFile.getChannel();
            //将文件指针移动到文件末尾
            fileChannel.position(fileChannel.size());
            byte[] bytes = "\n欧海涛".getBytes("UTF-8");
            ByteBuffer byteBuffer = ByteBuffer.allocate(bytes.length);
            byteBuffer.put(bytes);
            //将buffer切换到读模式
            byteBuffer.flip();
            //将数据写入到channel中
            fileChannel.write(byteBuffer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
