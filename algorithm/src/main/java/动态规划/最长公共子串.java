package 动态规划;

import java.util.Arrays;

/**
 * @author parry
 * @date 2020/12/15
 */
public class 最长公共子串 {
    
    public static void main(String[] args) {
        String text1 = "ABDCDAB";
        String text2 = "BDCABA";
        System.out.println(findMaxString(text1, text2));
    }
    
    /**
     * 最长子串
     */
    private static int findMaxString(String text1, String text2) {
        char[] str1 = text1.toCharArray();
        char[] str2 = text2.toCharArray();
        int[][] dp = new int[str1.length + 1][str2.length + 1];
//        记录最长子串的长度以及结束字符的下标
        int max = 0, maxIndex = -1;
        for (int i = 1; i <= str1.length; i++) {
            for (int j = 1; j <= str2.length; j++) {
                if (str1[i - 1] == str2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    if (max < dp[i][j]) {
                        max = dp[i][j];
                        maxIndex = i;
                    }
                } else {
                    dp[i][j] = 0;
                }
            }
        }
        
//        打印dp数组
        Arrays.stream(dp).forEach(x -> {
            Arrays.stream(x).forEach(y -> System.out.print(y + " "));
            System.out.println();
        });
        
//        打印最长公共子串
        if (max > 0) {
            for (int i = max; i > 0; i--) {
                System.out.print(str1[maxIndex - i]);
            }
        }
        System.out.println();
        
        return max;
    }
}
