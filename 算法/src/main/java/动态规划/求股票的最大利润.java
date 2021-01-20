package 动态规划;

/**
 * @author parry
 * @date 2020/12/16
 * 给出一只股票在一些时间节点的价格(按照时间顺序排列)
 * 求股票的最大利润
 * ps: 只能先买再卖
 */
public class 求股票的最大利润 {
    
    public static void main(String[] args) {
        int[] a = {9, 11, 8, 5, 7, 12, 16, 14};
        System.out.println(fun1(a));
        a = new int[]{9, 8, 7, 6, 5, 4, 3};
        System.out.println(fun1(a));
        a = new int[]{9, 8};
        System.out.println(fun1(a));
        a = new int[]{8, 9};
        System.out.println(fun1(a));
        a = new int[]{1, 2, 3, 4, 5};
        System.out.println(fun2(a));
        a = new int[]{5, 4, 3, 2, 1};
        System.out.println(fun2(a));
        a = new int[]{6, 1, 3, 2, 4, 7};
        System.out.println(fun2(a));
        a = new int[]{2, 1, 2, 1, 0, 1, 2};
        System.out.println(fun2(a));
        a = new int[]{1, 2, 3, 4, 5};
        System.out.println(fun3(a));
    }
    
    /**
     * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/
     * 动态规划解法
     * <p>
     * f(n)为在第n个时间节点卖出时股票的最低价格(不包含第n个时间点的价格)
     * 换句话说就是数组下标为 0~n-1 之间的最小元素
     * <p>
     * 股票的利润即为当前时间节点的价格减去f(n)
     * f(n) = min[f(n - 1), a[n - 1]]
     */
    private static int fun(int[] a) {
        if (a.length <= 1) {
            return 0;
        }
        int[] dp = new int[a.length];
        dp[1] = a[0];
        int maxDiff = a[1] - a[0];
        
        for (int i = 2; i < a.length; i++) {
            dp[i] = Math.min(dp[i - 1], a[i - 1]);
            maxDiff = Math.max(maxDiff, a[i] - dp[i]);
        }
        return Math.max(maxDiff, 0);
    }
    
    /**
     * 该方法是上面动态规划的简化版本
     * <p>
     * 目的是求第n个时间节点卖出时的最低价格，那么用一个变量存储即可
     * 由于是顺序遍历，min值始终是第 0 ~ n-1 之间最小的元素
     * <p>
     * 该方法不是通用方法，在用一维动态规划时可以考虑使用这种方法
     */
    private static int fun1(int[] a) {
        if (a.length == 0) {
            return 0;
        }
        int min = a[0], res = 0;
        for (int i = 1; i < a.length; i++) {
            if (min > a[i]) {
                min = a[i];
            } else {
                res = Math.max(res, a[i] - min);
            }
        }
        return res;
    }
    
    /**
     * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/
     * 我们把股票值用折线图画出来，原来只能够进行一次交易，那么只需要找到折线图中低点与高点的高度差最大(低点必须在高点之前)就是结果
     * 例如这张图:
     * https://pic.leetcode-cn.com/cc4ef55d97cfef6f9215285c7573027c4b265c31101dd54e8555a7021c95c927-file_1555699418271
     * 在(2,1)买入，(5,6)卖出即为结果
     * <p>
     * 现在能够进行多次交易,假设当前是时间点n，如果时间点n+1的股票值变低了，那么就进行一次交易，这样就能够使利益最大
     * 即(2,1)买入 (3,5)卖出 (4,3)买入 (5,6)卖出
     * 原因：利润实际上是两个点的y轴直线距离，根据折线图很容易看出这个结论
     */
    private static int fun2(int[] a) {
        if (a.length == 0 || a.length == 1) {
            return 0;
        }
        int res = 0, min = a[0];
        for (int i = 1; i < a.length; i++) {
            if (a[i - 1] > a[i]) {
                res += a[i - 1] - min;
                min = a[i];
            }
        }
        if (a[a.length - 2] <= a[a.length - 1]) {
            res += a[a.length - 1] - min;
        }
        return res;
    }
    
    /**
     * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iii/
     */
    private static int fun3(int[] a) {
        int[][] dp = new int[a.length][5];
        dp[0][0] = 0;
        dp[0][1] = -a[0];
        dp[0][2] = 0;
        dp[0][3] = -a[0];
        dp[0][4] = 0;
    
        for (int i = 1; i < a.length; i++) {
            dp[i][0] = dp[i - 1][0];
            dp[i][1] = Math.max(dp[i - 1][0] - a[i], dp[i - 1][1]);
            dp[i][2] = Math.max(dp[i - 1][1] + a[i], dp[i - 1][2]);
            dp[i][3] = Math.max(dp[i - 1][2] - a[i], dp[i - 1][3]);
            dp[i][4] = Math.max(dp[i - 1][3] + a[i], dp[i - 1][4]);
        }
        return Math.max(Math.max(Math.max(Math.max(dp[a.length - 1][0], dp[a.length - 1][1]), dp[a.length - 1][2]), dp[a.length - 1][3]), dp[a.length - 1][4]);
    }
    
}
