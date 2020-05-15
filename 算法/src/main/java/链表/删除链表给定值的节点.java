package 链表;

/**
 * @author parry
 * @date 2020/04/15
 */
public class 删除链表给定值的节点 {
    
    public static void main(String[] args) {
        System.out.println(fun(Node.build(new Integer[]{1,2,3,4,5,2}), 2));
    }
    
    private static Node<Integer> fun(Node<Integer> head, int val) {
//        哨兵节点,用于优化代码
        Node<Integer> sentinel = new Node<>(), current = sentinel;
        sentinel.next = head;
        
        while (current.next != null) {
            if (current.next.val == val) {
                current.next = current.next.next;
                continue;
            }
            current = current.next;
        }
        return sentinel.next;
    }
}
