package 树;

import java.util.*;

/**
 * @author parry
 * @date 2020/12/22
 * https://leetcode-cn.com/problems/binary-tree-zigzag-level-order-traversal/
 * 先了解树的层序遍历,即按层遍历
 */
public class 树的锯齿形层序遍历 {
    
    public static void main(String[] args) {
        System.out.println(zigzagLevelOrder1(build()));
    }
    
    /**
     * 栈
     */
    private static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }
        int floor = 1;
        List<List<Integer>> res = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        Queue<TreeNode> queue = new LinkedList<>();
        stack.push(root);
        
        while (!stack.isEmpty()) {
            List<Integer> tmp = new LinkedList<>();
            res.add(tmp);
            int size = stack.size();
            for (int i = 0; i < size; i++) {
                TreeNode poll = stack.pop();
                tmp.add(poll.val);
                if ((floor & 1) == 0) {
                    if (poll.right != null) {
                        queue.offer(poll.right);
                    }
                    if (poll.left != null) {
                        queue.offer(poll.left);
                    }
                } else {
                    if (poll.left != null) {
                        queue.offer(poll.left);
                    }
                    if (poll.right != null) {
                        queue.offer(poll.right);
                    }
                }
            }
            while (!queue.isEmpty()) {
                stack.push(queue.poll());
            }
            floor++;
        }
        return res;
    }
    
    /**
     * 双端队列
     */
    private static List<List<Integer>> zigzagLevelOrder1(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int floor = 1;
        List<List<Integer>> res = new LinkedList<>();
        while (!queue.isEmpty()) {
            Deque<Integer> deque = new LinkedList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                if (poll.left != null) {
                    queue.offer(poll.left);
                }
                if (poll.right != null) {
                    queue.offer(poll.right);
                }
                if ((floor & 1) == 1) {
//                    向队列尾部插入数据
                    deque.offerLast(poll.val);
                } else {
//                    向队列头部插入数据
                    deque.offerFirst(poll.val);
                }
            }
            floor++;
            res.add(new LinkedList<>(deque));
        }
        return res;
    }
    
    private static TreeNode build() {
        TreeNode treeNode15 = new TreeNode(15, null, null);
        TreeNode treeNode7 = new TreeNode(7, null, null);
        TreeNode treeNode10 = new TreeNode(10, null, null);
        TreeNode treeNode9 = new TreeNode(9, treeNode10, null);
        TreeNode treeNode20 = new TreeNode(20, treeNode15, treeNode7);
        TreeNode treeNode3 = new TreeNode(3, treeNode9, treeNode20);
        return treeNode3;
    }
}
