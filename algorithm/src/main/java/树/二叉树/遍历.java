package 树.二叉树;

import 树.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author parry
 * @date 2020/04/29
 */
public class 遍历 {
    
    /**
     * 按层遍历
     */
    private static void fun1() {
        TreeNode treeNode1 = build();
        Queue<TreeNode> treeNodes = new LinkedList<>();
        treeNodes.add(treeNode1);
        int floor = 1;
        while (treeNodes.peek() != null) {
            System.out.println("第" + floor++ + "层:");
            int size = treeNodes.size();
            for (int i = 0; i < size; i++) {
                TreeNode poll = treeNodes.poll();
                if (poll.getLeft() != null) {
                    treeNodes.add(poll.getLeft());
                }
                if (poll.getRight() != null) {
                    treeNodes.add(poll.getRight());
                }
                System.out.println(poll.getVal());
            }
        }
        System.out.println();
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
