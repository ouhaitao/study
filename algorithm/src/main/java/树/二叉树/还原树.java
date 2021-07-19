package 树.二叉树;

import 树.TreeNode;

/**
 * @author parry
 * @date 2021/07/14
 */
public class 还原树 {
    
    public static void main(String[] args) {
        String pre = "ABCDEFGH";
        String mid = "CBEDFAGH";
        TreeNode fun = fun(pre.toCharArray(), 0, pre.length() - 1, mid.toCharArray(), 0, mid.length() - 1);
        System.out.println(fun);
    }
    
    private static TreeNode fun(char[] pre, int preStart, int preEnd,
                                char[] mid, int midStart, int midEnd) {
        if (preStart >= preEnd || midStart >= midEnd) {
            return null;
        }
        int rootIndex = midStart;
        while (pre[preStart] != mid[rootIndex]) {
            rootIndex++;
        }
        TreeNode root = new TreeNode(pre[preStart]);
        root.left = fun(pre, preStart + 1, preStart + rootIndex, mid, midStart, rootIndex - 1);
        root.right = fun(pre, rootIndex - midStart + preStart + 1, preEnd, mid, rootIndex + 1, midEnd);
        return root;
    }
    
}
