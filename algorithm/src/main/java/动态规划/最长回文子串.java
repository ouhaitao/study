package 动态规划;

/**
 * @author parry 2022/09/26
 */
public class 最长回文子串 {
    
    public static void main(String[] args) {
        String s = "cbbd";
        System.out.println(dp(s));
        System.out.println(fun(s));
    }
    
    /**
     * 动态规划
     */
    public static String dp(String s) {
        if (s.length() < 2) {
            return s;
        }
        char[] chars = s.toCharArray();
        int length = chars.length;
        boolean[][] dp = new boolean[length][length];
        for (int i = 0; i < dp.length; i++) {
            dp[i][i] = true;
        }
        
        int maxLength = 1;
        int maxLengthBeginIndex = 0;
        for (int currentLength = 2; currentLength <= length; currentLength++) {
            for (int left = 0; left < length; left++) {
                int right = left + currentLength - 1;
                if (right >= length) {
                    break;
                }
                if (chars[left] != chars[right]) {
                    dp[left][right] = false;
                } else {
                    if (currentLength == 2) {
                        dp[left][right] = true;
                    } else {
                        dp[left][right] = dp[left + 1][right - 1];
                    }
                }
                if (dp[left][right] && (right - left + 1 > maxLength)) {
                    maxLength = right - left + 1;
                    maxLengthBeginIndex = left;
                }
            }
        }
        return s.substring(maxLengthBeginIndex, maxLengthBeginIndex + maxLength);
    }
    
    /**
     * 中心扩散
     * 以字符串中每一个字符为中心向两边进行扩散，得到最长的会问串
     * 由于回文串有两种类型，一种aba，一种是aa。前者是以b为中心向两边扩散，后者是以aa为中心向两边扩散。所以在扩散时需要考虑这两种情况
     */
    public static String fun(String s) {
        if (s.length() < 2) {
            return s;
        }
        
        char[] chars = s.toCharArray();
        int maxLength = 0;
        int begin = 0;
        for (int i = 0; i < chars.length; i++) {
            // 考虑aba的类型
            int len1 = expandAroundCenter(chars, i, i);
            // 考虑aa的类型
            int len2 = expandAroundCenter(chars, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > maxLength) {
                maxLength = len;
                // 以i为中心向两边扩散，所以向i的左边移动了len-1/2
                begin = i - (len - 1) / 2;
            }
        }
        return s.substring(begin, begin + maxLength);
    }
    
    public static int expandAroundCenter(char[] chars, int left, int right) {
        while (left >= 0 && right < chars.length) {
            if (chars[left] == chars[right]) {
                left--;
                right++;
            } else {
                break;
            }
        }
        // chars[left] != chars[right] 所以需要-2
        return (right - left + 1) - 2;
    }
}
