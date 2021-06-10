package 链表;

/**
 * @author parry
 * @date 2020/11/30
 */
public class 反转链表 {
    
    public static void main(String[] args) {
        System.out.println(flipAll(Node.build(new Integer[]{1, 2, 3, 4, 5})));
        System.out.println(flipPart(Node.build(new Integer[]{1, 2, 3, 4, 5}), 1, 5));
        System.out.println(flipPart(Node.build(new Integer[]{1, 2, 3, 4, 5}), 2, 4));
    }
    
    /**
     * 翻转整个链表
     */
    private static Node flipAll(Node head) {
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
    
    /**
     * 翻转链表位置为m到n的这部分链表
     * 一趟循环完成
     * 1<=m<=n<=链表长度
     */
    private static Node flipPart(Node head, int m, int n) {
        if (m == n) {
            return head;
        }
        Node start = head;
        int i = 1;
        for (; i < m; i++) {
            start = start.next;
        }
        Node before = start, after = start.next;
        before.next = null;
        for (; i < n; i++) {
            Node tmp = after.next;
            after.next = before;
            before = after;
            after = tmp;
        }
        return before;
    }
}
