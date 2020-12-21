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
        System.out.println(fun(a));
        a = new int[]{9, 8, 7, 6, 5, 4, 3};
        System.out.println(fun(a));
        a = new int[]{9, 8};
        System.out.println(fun(a));
        a = new int[]{8, 9};
        System.out.println(fun(a));
    }
    
    /**
     *  f(n)为在第n个时间节点卖出时股票的最低价格(不包含第n个时间点的价格)
     *  换句话说就是数组下标为 0~n-1 之间的最小元素
     *
     *  股票的利润即为当前时间节点的价格减去f(n)
     *  f(n) = min[f(n - 1), a[n - 1]]
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
}
