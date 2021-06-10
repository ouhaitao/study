package 动态规划;

import java.math.BigDecimal;
import java.util.Arrays;

/**
 * @author parry
 * @date 2020/12/14
 * 求n个骰子随机掷出,求骰子正面朝上的点数的和的概率
 */
public class n个骰子的点数 {
    
    public static void main(String[] args) {
        System.out.println(Arrays.toString(dicesProbability(11)));
    }
    
    /**
     * n个骰子的和在 n ~ 6n之间,根据排列组合,n个骰子的和个数总共为6的n次方
     * 那么每一个和的概率即为其出现的次数处于总个数
     *
     * 设f(n,m)是n个骰子和为m出现的次数
     * 将那个骰子分为第n个骰子与前n-1个骰子
     * 那么n个骰子和为m出现的次数可以分解为：(第n个骰子掷出1,前n-1个骰子掷出m-1的次数) + (第n个骰子掷出2,前n-1个骰子掷出m-2)...
     * 最后状态转移方程式为
     * f(n,m) = f(n-1,m-1) + f(n-1,m-2)... + f(n-1,m-i) [1<=i<=6, (m - i) >= (n - 1)]
     * i为第n个骰子的点数
     */
    public static double[] dicesProbability(int n) {
        if (n < 1) {
            throw new IllegalArgumentException("n can not smaller than 1");
        }
        
        int minSum = n, maxSum = 6 * n;
        double total = Math.pow(6, n);
        int[][] resCount = new int[n + 1][maxSum + 1];
        for (int i = 1; i <= 6; i++) {
            resCount[1][i] = 1;
        }
        for (int i = 2; i <= n; i++) {
            for (int j = i; j <= 6 * i; j++) {
                int tmp = j > 6 ? 6 : j - (i - 1);
                int sum = 0;
                while (tmp > 0) {
                    sum += resCount[i - 1][j - tmp];
                    tmp--;
                }
                resCount[i][j] = sum;
            }
        }
        double[] resProbability = new double[maxSum - minSum + 1];
        for (int i = n; i < resCount[n].length; i++) {
            resProbability[i - n] = new BigDecimal((double) resCount[n][i] / total).setScale(5, BigDecimal.ROUND_HALF_UP).doubleValue();
        }
        return resProbability;
    }
}
