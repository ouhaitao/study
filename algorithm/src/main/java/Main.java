import java.util.*;

/**
 * @author parry
 * @date 2021/07/09
 */
public class Main {
    
    private int res = 0;
    
    public static void main(String[] args) throws Exception {
        Main main = new Main();
        int[] scores = {4,5,6,5}, ages = {2,1,2,1};
        System.out.println(main.bestTeamScore(scores, ages));
        scores = new int[]{1, 3, 5, 10, 15};
        ages = new int[]{1, 2, 3, 4, 5};
        System.out.println(main.bestTeamScore(scores, ages));
        scores = new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
        ages = new int[]{811, 364, 124, 873, 790, 656, 581, 446, 885, 134};
        System.out.println(main.bestTeamScore(scores, ages));
        scores = new int[]{9,2,8,8,2};
        ages = new int[]{4,1,3,3,5};
        System.out.println(main.bestTeamScore(scores, ages));
        scores = new int[]{596,277,897,622,500,299,34,536,797,32,264,948,645,537,83,589,770};
        ages = new int[]{18,52,60,79,72,28,81,33,96,15,18,5,17,96,57,72,72};
        System.out.println(main.bestTeamScore(scores, ages));
    }
    
    public int bestTeamScore(int[] scores, int[] ages) {
        sort(scores, ages, 0, scores.length - 1);
        int[] dp = new int[scores.length];
        dp[0] = scores[0];
        int res = dp[0];
        for (int i = 1; i < scores.length; i++) {
            int j = i - 1;
            dp[i] = scores[i];
            for (; j >= 0; j--) {
                if (ages[i] >= ages[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + scores[i]);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }
    
    public void sort(int[] scores, int[] ages, int start, int end) {
        if (start >= end) {
            return;
        }
        int partition = partition(scores, ages, start, end);
        sort(scores, ages, start, partition - 1);
        sort(scores, ages, partition + 1, end);
    }
    
    public int partition(int[] scores, int[] ages, int start, int end) {
        int score = scores[end];
        int age = ages[end];
        int cur = start;
        for (int i = start; i < end; i++) {
            if (scores[i] < score || (scores[i] == score && ages[i] < age)) {
                int tmp = scores[cur];
                scores[cur] = scores[i];
                scores[i] = tmp;
                
                tmp = ages[cur];
                ages[cur] = ages[i];
                ages[i] = tmp;
                
                cur++;
            }
        }
        
        int tmp = scores[cur];
        scores[cur] = scores[end];
        scores[end] = tmp;
    
        tmp = ages[cur];
        ages[cur] = ages[end];
        ages[end] = tmp;
        return cur;
    }
    
    
}
