package 一些问题;

/**
 * @author parry
 * @date 2020/11/19
 */
public class 二进制中1的个数 {
    
    /**
     * 计算一个二进制数中有多少个1
     */
    public static void main(String[] args) {
        int num = 9;
        fun1(num);
        fun2(num);
        fun3(num);
    }
    
    /**
     * 每次右移一位，判断最后一位是否为1
     */
    private static void fun1(int num) {
        int count = 0;
//        负数右移左边会补1，所以需要变成负数，负数自身符号就是1所以count++
        if (num < 0) {
            count++;
            num = -num;
        }
        while (num != 0) {
            if ((num & 1) == 1) {
                count++;
            }
            num = num >> 1;
        }
        System.out.println(count);
    }
    
    /**
     * 将tmp左移来判断num的每一位是否为1
     */
    private static void fun2(int num) {
        int tmp = 1;
        int count = 0;
        for (int i = 0; i < 32; i++) {
            if ((num & tmp << i) != 0) {
                count++;
            }
        }
        System.out.println(count);
    }
    
    /**
     * n & (n-1)的作用是将n最右边的1变成0
     * eg: 1100 & 1011 = 1000
     */
    private static void fun3(int num) {
        int count = 0;
        while (num != 0) {
            num = num & (num - 1);
            count++;
        }
        System.out.println(count);
    }
}
