package 动态规划;

/**
 * @author parry
 * @date 2020/12/10
 * 从左上角开始，只能向右或者向下移动，最终到达右下角
 */
public class 礼物的最大值 {
    
    public static void main(String[] args) {
        int[][] gift = {
            {1, 10, 3, 8},
            {12, 2, 9, 6},
            {5, 7, 4, 11},
            {3, 7, 16, 5}
        };
        System.out.println(fun(gift));
    }
    
    private static int fun(int[][] gift) {
        if (gift == null || gift.length == 0 || gift[0].length == 0) {
            throw new IllegalArgumentException();
        }
        
        int[][] dp = new int[gift.length][gift[0].length];
        for (int i = 0; i < gift.length; i++) {
            for (int j = 0; j < gift[i].length; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = gift[i][j];
                } else if (i == 0) {
                    dp[i][j] = dp[i][j - 1] + gift[i][j];
                } else if (j == 0){
                    dp[i][j] = dp[i - 1][j] + gift[i][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]) + gift[i][j];
                }
            }
        }
        return dp[dp.length - 1][dp[0].length - 1];
    }
}
