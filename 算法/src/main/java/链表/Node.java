package 链表;

/**
 * @author parry
 * @date 2020/04/14
 */
public class Node<T> {
    
    public T val;
    public Node<T> next;
    
    public static <T> Node<T> build(T[] array) {
        Node<T> head = null, tmp = null;
        for (T t : array) {
            if (head == null) {
                head = new Node<>();
                head.val = t;
                head.next = null;
                tmp = head;
            } else {
                tmp.next = new Node<>();
                tmp = tmp.next;
                tmp.val = t;
                tmp.next = null;
            }
        }
        return head;
    }
    
    @Override
    public String toString() {
        return val + (next == null ? "" : "->" + next.toString());
    }
}
