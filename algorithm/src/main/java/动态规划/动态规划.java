package 动态规划;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * 如果是求问题的最优解，且该问题可以分解成若干个子问题，并且子问题之间还有重叠的更小子问题，就可以考虑用动态规划
 * 在应用动态规划之前要分析能否把大问题分解成小问题，分解后的每个小问题也存在最优解，如果小问题的最优解能组合起来得到
 * 整个问题的最优解，那么就可以用动态规划解决这个问题
 */
public class 动态规划 {
    
    public static void main(String[] args) {
    }
    
    //背包问题
    public static void backpack() {
        
        int[] w = {0, 2, 2, 6, 5, 4};//重量
        int[] v = {0, 6, 3, 5, 4, 6};//价值
        int c = 10;//背包最大重量
        int[][] res = new int[w.length + 1][c + 1];
        
        //初始化边界
        for (int i = 1; i < c + 1; i++) {
            if (i < w[1]) {
                res[1][i] = 0;
            } else {
                res[1][i] = v[1];
            }
        }
        
        //从放入第二个物体开始算起
        for (int n = 2; n < 6; n++) {
            for (int m = 1; m < 11; m++) {
                if (m < w[n]) {
                    res[n][m] = res[n - 1][m];
                } else {
                    res[n][m] = Math.max(res[n - 1][m], res[n - 1][m - w[n]] + v[n]);
                }
            }
        }
        System.out.println(res[5][10]);
    }
    
    //硬币找零
    private static void coin() {
        int n = 21;
        int[] res = new int[n + 1];
        res[0] = 0;
        res[1] = 1;
        res[2] = 2;
        for (int i = 3; i < res.length; i++) {
            if (i < 5)
                res[i] = Math.min(res[i - 1], res[i - 3]) + 1;
            else {
                int tmp = Math.min(res[i - 1], res[i - 3]);
                res[i] = Math.min(res[i - 5], tmp) + 1;
            }
        }
        System.out.println(res[n]);
    }
    
    
    
    /**
     * 切绳子
     * 把长度为length的绳子切成若干段（至少切一刀），使这些段的乘积最大
     * <p>
     * 递推公式 f(n) = MAX[i * (n - i), i * f(n - i)] (i > 3)
     * 在长度为i处切一刀，然后接下来选择不切，即结果为i * (n - i)；选择继续切，即结果为i * f(n - i)
     */
    public static void cutRope(int length) {
        if (length < 2) {
            System.out.println(0);
        }
        if (length == 2) {
            System.out.println(1);
        }
        if (length == 3) {
            System.out.println(2);
        }
        int[] dp = new int[length + 1];
        dp[0] = 0;
        dp[1] = 0;
        dp[2] = 1;
        dp[3] = 2;
        for (int i = 4; i <= length; i++) {
            int max = -1;
//            j >= i/2与 j <= i/2 结果重复
            for (int j = 1; j <= i / 2; j++) {
                int tmp = Math.max(j * (i - j), j * dp[i - j]);
                if (tmp > max) {
                    max = tmp;
                }
            }
            dp[i] = max;
        }
        System.out.println(dp[length]);
    }
}
