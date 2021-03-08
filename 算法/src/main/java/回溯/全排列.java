package 回溯;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @author parry
 * @date 2021/03/08
 * 回溯入门题目：
 * https://leetcode-cn.com/problems/permutations/
 */
public class 全排列 {
    
    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        System.out.println(permute(nums));
    }
    
    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        Deque<Integer> path = new LinkedList<>();
        boolean[] used = new boolean[nums.length];
        
        dfs(nums, path, used, res);
        
        return res;
    }
    
    /**
     *
     * @param nums 数字
     * @param path 路径，用于记录当前的题解
     * @param used 已使用的数字，空间换时间的处理方式
     * @param res 结果
     */
    private static void dfs(int[] nums, Deque<Integer> path, boolean[] used, List<List<Integer>> res) {
        if (path.size() == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }
        
        for (int i = 0; i < nums.length; i++) {
            if (!used[i]) {
                used[i] = true;
                path.addLast(nums[i]);
                dfs(nums, path, used, res);
                used[i] = false;
                path.removeLast();
            }
        }
    }
}
