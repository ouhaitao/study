package 一些问题;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author parry
 * @date 2020/11/18
 * 给出某二叉树的前序遍历和中序遍历还原二叉树
 * ps: 没有重复数字
 */
public class 重建二叉树 {
    
    private static class Node {
        int value;
        Node left;
        Node right;
    }
    
    public static void main(String[] args) {
        int[] per = {1, 2, 4, 7, 3, 5, 6, 8};
        int[] mid = {4, 7, 2, 1, 5, 3, 8, 6};
        Node node = fun(per, mid);
        System.out.println(node);
    }
    
    private static Node fun(int[] per, int[] mid) {
        int root = per[0];
//        从中序遍历中找到根节点的位置从而确认左子树跟右子树的节点个数
//        左子树的节点的下标为[0, rootIndex - 1] 右子树的节点下标为[rooIndex + 1, mid.length - 1]
        int rootIndex = findNodeIndex(mid, root);
        Node rootNode = new Node();
        rootNode.value = root;
        if (rootIndex > 0) {
            rootNode.left = fun(copy(per, 1, rootIndex), copy(mid, 0, rootIndex - 1));
        }
        if (rootIndex < mid.length - 1) {
            rootNode.right = fun(copy(per, 1 + rootIndex, per.length - 1), copy(mid, rootIndex + 1, mid.length - 1));
        }
        return rootNode;
    }
    
    /**
     * from、to都包含
     */
    private static int[] copy(int[] a, int from, int to) {
        if (from > to) {
            throw new IllegalArgumentException("from > to");
        }
        int[] res = new int[to - from + 1];
        int index = 0;
        for (int i = from; i <= to; i++) {
            res[index++] = a[i];
        }
        return res;
    }
    
    private static int findNodeIndex(int[] arr, int root) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == root) {
                return i;
            }
        }
        return -1;
    }
}
