package 树;

import 链表.Node;

/**
 * @author parry
 * @date 2020/12/21
 */
public class 求两个节点的最低公共祖先 {
    
    public static void main(String[] args) {
        TreeNode head = build();
        TreeNode[] res = new TreeNode[1];
        fun(head, new TreeNode(3, null, null), new TreeNode(7, null, null), res);
        System.out.println(res[0] == null ? null : res[0].getVal());
    }
    
    /**
     * 剑指offer 68题
     * 对于一个节点来说，如果p，q一个在左子树，一个在右子树，则该节点是p，q的公共祖先
     * 在后序遍历中，由于是先遍历的子节点，所以第一个满足上述条件的节点即为最低公共祖先
     *
     * @param res 结果，由于java只有值传递，所以通过这种方式保存结果
     * @return 当前节点以及子树中是否找到p或者q
     */
    private static boolean fun(TreeNode node, TreeNode p, TreeNode q, TreeNode[] res) {
        if (node == null) {
            return false;
        }
//        自己也可以是自己的祖先节点
        boolean flag = false;
        if (node.val == p.val || node.val == q.val) {
            flag = true;
        }
        
        boolean leftRes = fun(node.left, p, q, res);
//        已找到结果，直接返回
        if (res[0] != null) {
            return true;
        }
//        如果当前节点是p或者q 且 左子树找到了另一个节点
//        结果是当前节点
        if (flag && leftRes) {
            res[0] = node;
            return true;
        }
        
        boolean rightRes = fun(node.right, p, q, res);
        if (res[0] != null) {
            return true;
        }
        if (flag && rightRes) {
            res[0] = node;
            return true;
        }
        
//        在左子树与右子树找到了p、q 当前节点即为结果
        if (leftRes && rightRes) {
            res[0] = node;
            return true;
        }
        
        return leftRes || rightRes || flag;
    }
    
    private static TreeNode build() {
        TreeNode treeNode7 = new TreeNode(7, null, null);
        TreeNode treeNode6 = new TreeNode(6, null, null);
        TreeNode treeNode5 = new TreeNode(5, null, null);
        TreeNode treeNode4 = new TreeNode(4, null, null);
        TreeNode treeNode3 = new TreeNode(3, treeNode6, treeNode7);
        TreeNode treeNode2 = new TreeNode(2, treeNode4, treeNode5);
        TreeNode treeNode1 = new TreeNode(1, treeNode2, treeNode3);
        return treeNode1;
    }
}
