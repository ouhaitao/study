package 动态规划;

import java.util.Arrays;

/**
 * @author parry
 * @date 2020/12/15
 * https://leetcode-cn.com/problems/longest-common-subsequence/
 */
public class 最长公共子序列 {
    
    public static void main(String[] args) {
        String text1 = "abcde";
        String text2 = "acebd";
        System.out.println(findMaxLengthStr(text1, text2));
    }
    
    /**
     * dp[i][j]表示text1前i个字符组成的字符串与text2前j个字符组成的字符串最长公共子序列的长度
     * 如果text1[i] == text2[j]，那么dp[i][j] = dp[i - 1][j - 1] + 1
     * 否则text1或text2向前推进1个字符串中最长的子序列长度为dp[i][j]
     */
    private static int findMaxLengthStr(String text1, String text2) {
        char[] str1 = text1.toCharArray();
        char[] str2 = text2.toCharArray();
        int[][] dp = new int[str1.length + 1][str2.length + 1];
        
        for (int i = 1; i <= str1.length; i++) {
            for (int j = 1; j <= str2.length; j++) {
                if (str1[i - 1] == str2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
                
            }
        }
    
        Arrays.stream(dp).forEach(x -> {
            Arrays.stream(x).forEach(y -> System.out.print(y + " "));
            System.out.println();
        });
        
        return dp[str1.length][str2.length];
    }
}
