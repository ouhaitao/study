package 一些问题;

import 树.TreeNode;

/**
 * @author parry
 * @date 2020/12/08
 * 将二叉搜索树转换成一个有序的双向链表
 * 不能创建任何新节点
 */
public class 二叉搜索树转双向链表 {
    
    public static void main(String[] args) {
        TreeNode node = build();
        TreeNode linkNode = fun(node, null);
        while (linkNode.getLeft() != null) {
            linkNode = linkNode.getLeft();
        }
        while (linkNode != null) {
            System.out.print(linkNode.getVal() + "->");
            linkNode = linkNode.getRight();
        }
    }
    
    /**
     * 中序遍历
     * 二叉搜索树的中序遍结果就是一个顺序的链表
     * @param node 树节点
     * @param lastNodeInLink 顺序链表的最后一个节点
     * @return lastNodeInLink
     */
    private static TreeNode fun(TreeNode node, TreeNode lastNodeInLink) {
        if (node == null) {
            return lastNodeInLink;
        }
//        遍历左节点
        lastNodeInLink = fun(node.getLeft(), lastNodeInLink);
//        遍历当前节点
        node.setLeft(lastNodeInLink);
        if (lastNodeInLink != null) {
            lastNodeInLink.setRight(node);
        }
        lastNodeInLink = node;
//        遍历右节点
        return fun(node.getRight(), lastNodeInLink);
    }
    
    private static TreeNode build() {
        TreeNode treeNode3 = new TreeNode(3, null, null);
        TreeNode treeNode5 = new TreeNode(5, null, null);
        TreeNode treeNode7 = new TreeNode(7, null, null);
        TreeNode treeNode9 = new TreeNode(9, null, null);
        TreeNode treeNode11 = new TreeNode(11, null, null);
        TreeNode treeNode13 = new TreeNode(13, null, null);
        TreeNode treeNode15 = new TreeNode(15, null, null);
        TreeNode treeNode17 = new TreeNode(17, null, null);
//        TreeNode treeNode3 = null;
//        TreeNode treeNode5 = null;
//        TreeNode treeNode7 = null;
//        TreeNode treeNode9 = null;
//        TreeNode treeNode11 = null;
//        TreeNode treeNode13 = null;
//        TreeNode treeNode15 = null;
//        TreeNode treeNode17 = null;
        TreeNode treeNode4 = new TreeNode(4, treeNode3, treeNode5);
        TreeNode treeNode8 = new TreeNode(8, treeNode7, treeNode9);
        TreeNode treeNode12 = new TreeNode(12, treeNode11, treeNode13);
        TreeNode treeNode16 = new TreeNode(16, treeNode15, treeNode17);
        TreeNode treeNode6 = new TreeNode(6, treeNode4, treeNode8);
        TreeNode treeNode14 = new TreeNode(14, treeNode12, treeNode16);
        TreeNode treeNode10 = new TreeNode(10, treeNode6, treeNode14);
        return treeNode10;
    }
}
