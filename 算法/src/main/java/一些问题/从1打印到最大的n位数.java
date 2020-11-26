package 一些问题;

import java.util.Arrays;

/**
 * @author parry
 * @date 2020/11/24
 */
public class 从1打印到最大的n位数 {
    
    /**
     * 写代码之前需要考虑异常情况，例如本题给出的数可能超过计算机能够表示的数字的最大范围
     */
    public static void main(String[] args) {
        fun(2);
    }
    
    private static void fun(int n) {
        if (n <= 0) {
            System.out.println("无效长度");
            return;
        }
        char[] num = new char[n];
        Arrays.fill(num, '0');
        while (true) {
            System.out.println(num);
        
            int i = num.length - 1;
            for (; i >= 0; i--) {
                if (num[i] != '9') {
                    num[i]++;
                    break;
                } else {
                    num[i] = '0';
                }
            }
            if (i < 0) {
                break;
            }
        }
    }
}
