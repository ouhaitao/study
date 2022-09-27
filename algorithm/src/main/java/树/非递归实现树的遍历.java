package 树;

import java.util.Stack;

/**
 * @author parry 2022/08/17
 * @see <a href="https://blog.csdn.net/qq_56713382/article/details/124897917"/>
 */
public class 非递归实现树的遍历 {
    
    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
    
        node5.left = node3;
        node5.right = node6;
        node3.left = node2;
        node3.right = node4;
        node2.left = node1;
        
        preOrder(node5);
        System.out.println("------------------");
        midOrder(node5);
        System.out.println("------------------");
        postOrder(node5);
    }
    
    /**
     * 前序
     */
    public static void preOrder(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            System.out.println(cur.val);
            if (cur.right != null) {
                stack.push(cur.right);
            }
            if (cur.left != null) {
                stack.push(cur.left);
            }
        }
    }
    
    /**
     * 中序
     */
    public static void midOrder(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (!stack.isEmpty() || cur != null) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            System.out.println(cur.val);
            cur = cur.right;
        }
    }
    
    /**
     * 后续
     */
    public static void postOrder(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root, pre = null;
        while (!stack.isEmpty() || cur != null) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.peek();
            if (cur.right == null || cur.right == pre) {
                stack.pop();
                System.out.println(cur.val);
                pre = cur;
                cur = null;
            } else {
                cur = cur.right;
            }
        }
    }
}
