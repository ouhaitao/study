package 动态规划;

/**
 * @author parry
 * @date 2020/04/13
 * 数组中连续的元素组成的数组为数组的子节点
 * 子数组中节点的和称为子数组的和
 */
public class 计算数组的子数组最大和 {
    
    public static void main(String[] args) {
        int[] a = {1, -2, 3, 10, -4, 7, 2, -5};
        System.out.println(fun(a));
    }
    
    /**
     * 设f(i)函数为以数组a下标为i的元素为结尾的子数组的最大和
     * 当i = 0时, f(i) = a[0];
     * 当 i>0 && f(i - 1) > 0 时, f(i) = f(i - 1) + a[i]
     * 否则 f(i) = a[i]
     */
    private static int fun(int[] a) {
        int[] dp = new int[a.length];
        dp[0] = a[0];
        for (int i = 1; i < a.length; i++) {
            if (dp[i - 1] <= 0) {
                dp[i] = a[i];
            } else {
                dp[i] = dp[i - 1] + a[i];
            }
        }
        int max = dp[0];
        for (int i : dp) {
            if (i > max) {
                max = i;
            }
        }
        return max;
    }
}
