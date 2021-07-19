package 动态规划;

/**
 * @author parry
 * @date 2021/07/15
 * https://leetcode-cn.com/problems/longest-increasing-subsequence/solution/dong-tai-gui-hua-er-fen-cha-zhao-tan-xin-suan-fa-p/
 */
public class 最长递增子序列 {
    
    public static void main(String[] args) {
        System.out.println(lengthOfLIS(new int[]{10,9,2,5,3,7,101,18}));
    }
    
    public static int lengthOfLIS(int[] nums) {
        if (nums.length == 1) {
            return 1;
        }
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int res = 1;
        for (int i = 1; i < nums.length; i++) {
            int tmp = 1;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    tmp = Math.max(dp[j] + 1, tmp);
                }
            }
            dp[i] = tmp;
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}
