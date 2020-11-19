package 一些问题;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @author parry
 * @date 2020/11/12
 */
public class 找出数组中重复的数字 {
    
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7,8,9,1};
        byte b = (byte) 128;
        System.out.println(Integer.toBinaryString(b));;
        fun1(arr);
        fun2(arr);
        arr = new int[]{100, 200, 300, 400, 500, 600, 700, 800, 900, 100};
        fun3(arr);
        arr = new int[] {2, 3, 1, 0, 2, 5, 3};
        fun4(arr);
    }
    
    /**
     * hash表 空间消耗高
     */
    private static void fun1(int[] arr) {
        HashSet<Integer> set = new HashSet<>(arr.length);
        for (int i : arr) {
            if (!set.add(i)) {
                System.out.println("重复:" + i);
                return;
            }
        }
    }
    
    /**
     * 位移 数组中的值受res类型长度限制，太大会溢出
     */
    private static void fun2(int[] arr) {
        int res = 0;
        for (int i : arr) {
            int tmp = res;
            if (tmp == (res |= (1 << i))) {
                System.out.println("重复:" + i);
                return;
            }
        }
    }
    
    /**
     * 1 byte等于8位
     */
    private static void fun3(int[] arr) {
//        模拟申请1M的空间，理论上可以申请无限个空间
        byte[] res = new byte[1024];
        for (int i : arr) {
            byte b = res[i / 8];
            byte tmp = (byte) (b | (1 << (i - i / 8 * 8)));
            if (res[i / 8] == tmp) {
                System.out.println("重复:" + i);
                return;
            }
            res[i / 8] = tmp;
        }
    }
    
    /**
     * 时间复杂度 O(n) 空间复杂度O(1)
     * 数组中最大值必须小于数组长度
     * 思路：遍历数组执行 将值为i的数字放到arr[i]位置上 这个操作,直到发现重复数字
     */
    private static void fun4(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int tmp = arr[i];
            if (tmp == i) {
                continue;
            }
            if (tmp == arr[tmp]) {
                System.out.println("重复:" + tmp);
                return;
            }
            arr[i] = arr[tmp];
            arr[tmp] = tmp;
        }
    }
    
}
