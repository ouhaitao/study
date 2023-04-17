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
     * 以字符text1[i] text[j]作为结尾的字符串
     */
    private static int findMaxString(String text1, String text2) {
        int[][] dp = new int[text1.length()][text2.length()];
        // 初始化边界条件
        for (int i = 0; i < text1.length(); i++) {
            if (text1.charAt(i) == text2.charAt(0)) {
                dp[i][0] = 1;
            }
        }
        for (int i = 0; i < text2.length(); i++) {
            if (text1.charAt(0) == text2.charAt(i)) {
                dp[0][i] = 1;
            }
        }
//        记录最长子串的长度以及结束字符的下标
        int max = 0, maxIndex = -1;
        for (int i = 1; i < text1.length(); i++) {
            for (int j = 1; j < text2.length(); j++) {
                if (text1.charAt(i) == text2.charAt(j)) {
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
            char[] str1 = text1.toCharArray();
            for (int i = max; i > 0; i--) {
                System.out.print(str1[maxIndex - i + 1]);
            }
        }
        System.out.println();
        
        return max;
    }
    
}
