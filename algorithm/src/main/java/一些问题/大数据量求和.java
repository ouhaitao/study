package 一些问题;

import java.util.Arrays;

/**
 * @author parry
 * @date 2020/02/20
 * 一千万个数求和
 */
public class 大数据量求和 {
    
    
    /**
     * fork join
     */
    public static void main(String[] args) {
        long[] data = new long[10000000];
        for (int i = 0; i < data.length; i++) {
            data[i] = i;
        }
        Arrays.stream(data).parallel().reduce(Long::sum).ifPresent(System.out::println);
    }

}
