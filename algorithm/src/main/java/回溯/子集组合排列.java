package 回溯;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @author parry
 * @date 2021/05/20
 * https://mp.weixin.qq.com/s?__biz=MzAxODQxMDM0Mw==&mid=2247485007&idx=1&sn=ceb42ba2f341af34953d158358c61f7c&chksm=9bd7f847aca071517fe0889d2679ead78b40caf6978ebc1d3d8355d6693acc7ec3aca60823f0&scene=21#wechat_redirect
 * 这三个问题的思路相似，区别在于何时记录结果，结果是否有顺序
 */
public class 子集组合排列 {
    
    public static void main(String[] args) {
        int[] arr = {1, 2, 3};
        System.out.println(subset(arr));
        int n = 4;
        int k = 2;
        System.out.println(combine(n, k));
    
        System.out.println(permute(arr));
    }
    
    /**
     * 求子集
     */
    private static List<List<Integer>> subset(int[] arr) {
        Deque<Integer> path = new LinkedList<>();
        List<List<Integer>> res = new LinkedList<>();
        subsetFun(arr, path, 0, res);
        return res;
    }
    
    private static void subsetFun(int[] arr, Deque<Integer> path, int begin, List<List<Integer>> res) {
//        子集的结果包含了决策树的所有节点，所以每次遍历都需要记录一次
        res.add(new ArrayList<>(path));
//        这里也可以去掉，因为不满足下面循环的条件
        if (begin == arr.length) {
            return;
        }
//        子集的结果没有顺序，所以在遍历第二条路径时，已经遍历过的数字不能再用了，这里的i从begin开始，画一个树就很容易看出来
        for (int i = begin; i < arr.length; i++) {
            path.addLast(arr[i]);
            subsetFun(arr, path, i + 1, res);
            path.removeLast();
        }
    }
    
    /**
     * 组合
     * 输入两个数字 n, k，算法输出 [1..n] 中 k 个数字的所有不重复组合。
     */
    private static List<List<Integer>> combine(int n, int k) {
        int[] arr = new int[n];
        for (int i = 1; i <= n; i++) {
            arr[i - 1] = i;
        }
        Deque<Integer> path = new LinkedList<>();
        List<List<Integer>> res = new LinkedList<>();
        combineFun(arr, path, 0, res, k);
        return res;
    }
    
    private static void combineFun(int[] arr, Deque<Integer> path, int begin, List<List<Integer>> res, int k) {
//        组合的结果只有决策树的叶子节点，所以只记录叶子结点
        if (path.size() == k) {
            res.add(new ArrayList<>(path));
            return;
        }
//        无顺序 所以i从begin开始
        for (int i = begin; i < arr.length; i++) {
            path.addLast(arr[i]);
            combineFun(arr, path, i + 1, res, k);
            path.removeLast();
        }
    }
    
    /**
     * 求排列
     * 排列是有顺序的
     */
    private static List<List<Integer>> permute(int[] arr) {
        Deque<Integer> path = new LinkedList<>();
        List<List<Integer>> res = new LinkedList<>();
        boolean[] used = new boolean[arr.length];
        permuteFun(arr, path, res, used);
        return res;
    }
    
    private static void permuteFun(int[] arr, Deque<Integer> path, List<List<Integer>> res, boolean[] used) {
//        全排列的结果只有决策树的叶子节点，所以只记录叶子结点
        if (path.size() == arr.length) {
            res.add(new ArrayList<>(path));
            return;
        }
//        有顺序，所以在遍历第二条路径时，已经遍历过的数字还可以再用，所以需要记录数字是否使用，并且每次都需要遍历全部数组
        for (int i = 0; i < arr.length; i++) {
            if (used[i]) {
                continue;
            }
            path.addLast(arr[i]);
            used[i] = true;
            permuteFun(arr, path, res, used);
            used[i] = false;
            path.removeLast();
        }
    }
}
