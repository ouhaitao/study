package 排序;

import java.util.Arrays;

/**
 * @author parry
 * @date 2020/11/19
 * 时间复杂度 O(n²) 空间复杂度O(1)
 */
public class 冒泡排序 {
    
    public static void main(String[] args) {
        int[] a = {5, 4, 3, 2, 1};
        System.out.println(Arrays.toString(fun(a)));
    }
    
    private static int[] fun(int[] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = i + 1; j < a.length; j++) {
                if (a[i] > a[j]) {
                    int tmp = a[i];
                    a[i] = a[j];
                    a[j] = tmp;
                }
            }
        }
        return a;
    }
}
