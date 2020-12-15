package 动态规划;

/**
 * @author parry
 * @date 2020/12/10
 */
public class 把数字翻译成字符串 {
    
    public static void main(String[] args) {
        char[] map = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        System.out.println(fun(12258));
    }
    
    private static int fun(int num) {
        if (num < 0) {
            num = -num;
        }
        char[] numChar = String.valueOf(num).toCharArray();
        if (numChar.length == 1) {
            return 1;
        }
        int[] dp = new int[numChar.length];
        dp[0] = 1;
        int firstDoubleDigit = toDigit(numChar[0], numChar[1]);
        if (firstDoubleDigit <= 25 && firstDoubleDigit >= 10) {
            dp[1] = 2;
        } else {
            dp[1] = 1;
        }
        for (int i = 2; i < numChar.length; i++) {
            int tmp = toDigit(numChar[i - 1], numChar[i]);
            if (tmp <= 25 && tmp >= 10) {
                dp[i] = dp[i - 1] + dp[i - 2];
            } else {
                dp[i] = dp[i - 1];
            }
        }
        return dp[dp.length - 1];
    }
    
    private static int toDigit(char high, char low) {
        return (high - '0') * 10 + (low - '0');
    }
}
