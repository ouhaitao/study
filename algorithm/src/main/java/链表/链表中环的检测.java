package 链表;

/**
 * @author parry
 * @date 2020/04/15
 */
public class 链表中环的检测 {
    
    public static void main(String[] args) {
        Node<Integer> head = Node.build(new Integer[]{1, 2, 3, 4, 5, 6});
        Node tmp = head;
        while (tmp.next != null) {
            tmp = tmp.next;
        }
        System.out.println(fun(head));
        tmp.next = head.next;
        System.out.println(fun(head));
    }
    
    /**
     * slow每次向后移动一步 fast每次向后移动两步
     * 当最后slow与fast相遇时即表示有环
     * 相遇的地方即为环中的一个节点
     */
    private static boolean fun(Node head) {
        Node slow = head, fast;
        if (slow == null) {
            return false;
        }
        fast = slow.next;
        while (fast != null) {
            if (slow == fast) {
                System.out.println("相遇节点:" + slow.val);
                return true;
            }
            slow = slow.next;
            
            fast = fast.next;
            if (fast != null) {
                fast = fast.next;
            } else {
                return false;
            }
        }
        return false;
    }
}
