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
        tmp.next = head;
        System.out.println(fun(head));
    }
    
    private static boolean fun(Node head) {
        Node slow = head, fast;
        if (slow == null) {
            return false;
        }
        fast = slow.next;
        int count = 0;
        while (fast != null) {
            fast = fast.next;
            if (slow == fast) {
                System.out.println(count);
                return true;
            }
            slow = slow.next;
            fast = fast.next;
            count++;
        }
        System.out.println(count);
        return false;
    }
}
