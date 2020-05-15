package 树.二叉树;

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
        Node node1 = build();
        Queue<Node> nodes = new LinkedList<>();
        nodes.add(node1);
        int floor = 1;
        while (nodes.peek() != null) {
            System.out.println("第" + floor++ + "层:");
            int size = nodes.size();
            for (int i = 0; i < size; i++) {
                Node poll = nodes.poll();
                if (poll.getLeft() != null) {
                    nodes.add(poll.getLeft());
                }
                if (poll.getRight() != null) {
                    nodes.add(poll.getRight());
                }
                System.out.println(poll.getVal());
            }
        }
        System.out.println();
    }
    
    private static Node build() {
        Node node7 = new Node(7, null, null);
        Node node6 = new Node(6, null, null);
        Node node5 = new Node(5, null, null);
        Node node4 = new Node(4, null, null);
        Node node3 = new Node(3, node6, node7);
        Node node2 = new Node(2, node4, node5);
        Node node1 = new Node(1, node2, node3);
        return node1;
    }
}
