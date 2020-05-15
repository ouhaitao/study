package concurrent;

/**
 * @author parry
 * @date 2019/10/21
 */
public class ConcurrentHashMapExample {
    
    public static void main(String[] args) throws InterruptedException {
        String key="1";
        int h = key.hashCode();
        int hash=(h ^ (h >>> 16)) & 0x7fffffff;
        System.out.println(Integer.toBinaryString(hash));
        System.out.println(Integer.toBinaryString(hash&(8-1)));
        System.out.println(Integer.toBinaryString(hash&(16-1)));
        System.out.println(Integer.toBinaryString(hash&(32-1)));
    }
    
    public static void f() {
        int rs = Integer.numberOfLeadingZeros(16);
        int tmp = (1 << 15);
        System.out.println(Integer.toBinaryString(16));
        System.out.println(Integer.toBinaryString(rs));
        System.out.println(Integer.toBinaryString(tmp));
        System.out.println(Integer.toBinaryString(rs | tmp));
        System.out.println(rs >>> 16);
    }
    
}

