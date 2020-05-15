package 链表;

/**
 * @author parry
 * @date 2020/04/15
 */
public class 求链表的中间节点 {
    
    public static void main(String[] args) {
        Node<Integer> head = Node.build(new Integer[]{1, 2, 3, 4, 5, 6});
        System.out.println(fun(head));
        System.out.println(fun1(head));
    }
    
    /**
     * 如果有两个中间结点，则返回第 一 个中间结点。
     */
    private static Node fun(Node head) {
        Node slow = head, fast;
        if (slow == null) {
            return null;
        }
        if (slow.next == null) {
            return slow;
        }
        fast = slow;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next;
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }
    
    /**
     * 如果有两个中间结点，则返回第 二 个中间结点。
     */
    private static Node fun1(Node head) {
        Node slow = head, fast;
        if (slow == null) {
            return null;
        }
        fast = slow;
        while (fast != null && fast.next != null) {
            fast = fast.next;
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }
}
