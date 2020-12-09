package 链表;

/**
 * @author parry
 * @date 2020/11/30
 */
public class 反转链表 {
    
    public static void main(String[] args) {
        System.out.println(fun(Node.build(new Integer[]{1, 2, 3, 4, 5})));
    }
    
    private static Node fun(Node head) {
        if (head == null) {
            throw new IllegalArgumentException("head can not be null");
        }
        
        if (head.next == null) {
            return head;
        }
        
        Node before = head, after = head.next;
        while (after != null) {
            Node tmp = after.next;
            after.next = before;
            before = after;
            after = tmp;
        }
        head.next = null;
        return before;
    }
}
