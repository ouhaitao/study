package 回溯;

import java.util.*;

/**
 * @author parry
 * @date 2021/03/08
 */
public class 组合总和 {
    
    public static void main(String[] args) {
        int[] candidates = {2, 3, 6, 7};
        int target = 7;
        System.out.println(combinationSum(candidates, target));
        candidates = new int[]{2, 5, 2, 1, 2};
        target = 5;
        System.out.println(combinationSum2(candidates, target));
    }
    
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new LinkedList<>();
        Deque<Integer> path = new LinkedList<>();
        
        dfs1(candidates, target, 0, path, res);
        
        return res;
    }
    
    /**
     * https://leetcode-cn.com/problems/combination-sum/solution/hui-su-suan-fa-jian-zhi-python-dai-ma-java-dai-m-2/
     *
     * @param candidates 给定参数
     * @param target     给定数字
     * @param begin      遍历起始位置，用于去重
     * @param path       遍历路径，代表一个题解
     * @param res        结果
     */
    private static void dfs1(int[] candidates, int target, int begin, Deque<Integer> path, List<List<Integer>> res) {
        if (target == 0) {
            res.add(new ArrayList<>(path));
            return;
        } else if (target < 0) {
            return;
        }
        
        for (int i = begin; i < candidates.length; i++) {
            path.addLast(candidates[i]);
            dfs1(candidates, target - candidates[i], i, path, res);
            path.removeLast();
        }
    }
    
    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new LinkedList<>();
        Deque<Integer> path = new LinkedList<>();
        boolean[] used = new boolean[candidates.length];

//        重要
        quickSort(candidates, 0, candidates.length - 1);
        
        dfs2(candidates, target, 0, path, res, used);
        
        return res;
    }
    
    /**
     * https://leetcode-cn.com/problems/combination-sum-ii/solution/hui-su-suan-fa-jian-zhi-python-dai-ma-java-dai-m-3/
     *
     * @param candidates 给定参数，已排好序
     * @param target     给定目标
     * @param begin      遍历起始位置，用于去重
     * @param path       路径，题解
     * @param res        结果
     * @param used       使用情况
     */
    private static void dfs2(int[] candidates, int target, int begin, Deque<Integer> path, List<List<Integer>> res, boolean[] used) {
        if (target == 0) {
            res.add(new ArrayList<>(path));
            return;
        } else if (target < 0) {
            return;
        }
        
        for (int i = begin; i < candidates.length; i++) {
//            由于已经排好序，所以相同元素会相邻，并且第一个值为n的元素的结果已经包含了所有值为n的结果，后续相同值的元素不需要遍历
            if (i > begin && candidates[i] == candidates[i - 1]) {
                continue;
            }
            if (!used[i]) {
                path.addLast(candidates[i]);
                used[i] = true;
                dfs2(candidates, target - candidates[i], i + 1, path, res, used);
                path.removeLast();
                used[i] = false;
            }
        }
    }
    
    private static void quickSort(int[] a, int start, int end) {
        if (start >= end) {
            return;
        }
        int keyIndex = partition(a, start, end);
        quickSort(a, start, keyIndex - 1);
        quickSort(a, keyIndex + 1, end);
    }
    
    private static int partition(int[] a, int start, int end) {
        int i = start;
        int key = a[end];
        for (int j = start; j < end; j++) {
            if (a[j] < key) {
                int tmp = a[i];
                a[i] = a[j];
                a[j] = tmp;
                i++;
            }
        }
        a[end] = a[i];
        a[i] = key;
        return i;
    }
}
