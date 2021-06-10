package 位运算;

import java.util.Arrays;

/**
 * @author parry
 * @date 2020/12/11
 * 剑指offer 56题
 */
public class 数组中数字出现的次数 {
    
    public static void main(String[] args) {
        int[] a = {2, 4, 3, 6, 3, 2, 5, 5};
        System.out.println(Arrays.toString(fun2(a)));
        a = new int[]{3,4,3,3};
        System.out.println(fun3(a));
    }
    
    /**
     * 先求一个简单的问题
     * 一个整型数组里除 一 个数字之外，其他数字都出现了两次。请找出这个数字
     * 同一个数字自己异或自己结果为0，那么把数组中所有元素异或，结果就是只出现了一次的那个数字
     */
    private static int fun1(int[] a) {
        int tmp = a[0];
        for (int i = 1; i < a.length; i++) {
            tmp ^= a[i];
        }
        return tmp;
    }
    
    /**
     * 一个整型数组里除两个数字之外，其他数字都出现了两次。请找出这两个数字
     * @link https://leetcode-cn.com/problems/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-lcof/solution/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-by-leetcode
     */
    private static int[] fun2(int[] a) {
//        数组中元素全部异或
        int tmp = a[0];
        for (int i = 1; i < a.length; i++) {
            tmp ^= a[i];
        }

//        取得结果中从右往左第一个为1的位
        int num = 1;
        while ((tmp & 1) == 0) {
            tmp = tmp >>> 1;
            num = num << 1;
        }

//        将数组a分成两个子数组,按照二进制对应位置上的数字是否与num对应位置上的数字一致
//        由于异或结果第n位为1的话说明两个数字第n位数字不相同,反之则相同
//        所以最终两个子数组中各包含1个只出现1次的元素,其余都是出现两次的元素
        int[] a1 = new int[a.length];
        int index1 = 0;
        int[] a2 = new int[a.length];
        int index2 = 0;
        for (int data : a) {
            if ((data & num) == 0) {
                a1[index1++] = data;
            } else {
                a2[index2++] = data;
            }
        }

//        从子数组中取得只出现1次的元素
        int res1 = a1[0];
        for (int i = 1; i < index1; i++) {
            res1 ^= a1[i];
        }
        
        int res2 = a2[0];
        for (int i = 1; i < index2; i++) {
            res2 ^= a2[i];
        }
        return new int[]{res1, res2};
    }
    
    /**
     * 在一个数组中除一个数字只出现一次之外，其他数字都出现了三次。请找出那个只出现一次的数字。
     */
    private static int fun3(int[] a) {
        int[] sum = new int[32];
        for (int value : a) {
            int tmp = 1;
            for (int j = 0; j < 32; j++) {
                if ((value & tmp) != 0) {
                    sum[j]++;
                }
                tmp = tmp << 1;
            }
        }
        int res = 0;
        for (int i = 0; i < 32; i++) {
            if ((sum[i] % 3) != 0) {
                res += 1 << i;
            }
        }
        return res;
    }
}
