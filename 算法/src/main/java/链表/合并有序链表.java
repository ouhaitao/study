package 链表;

/**
 * @author parry
 * @date 2020/11/30
 */
public class 合并有序链表 {
    
    public static void main(String[] args) {
        Node<Integer> head1 = Node.build(new Integer[]{1, 3, 5, 7});
        Node<Integer> head2 = Node.build(new Integer[]{1, 4, 6, 8});
        System.out.println(fun(head1, head2));
    }
    
    private static Node<Integer> fun(Node<Integer> head1, Node<Integer> head2) {
        if (head1 == null || head2 == null) {
            throw new IllegalArgumentException("head can not be null");
        }

//        base为基准链，作为比较的基准
//        operation作为操作链，作为
        Node<Integer> base, operation, result;
        if (head1.val <= head2.val) {
            base = head1;
            operation = head2;
        } else {
            base = head2;
            operation = head1;
        }
        result = base;
        
        while (operation != null) {
            if (operation.val >= base.val && (base.next == null || operation.val < base.next.val)) {
                Node<Integer> tmp = new Node<>();
                tmp.val = operation.val;
                tmp.next = base.next;
                base.next = tmp;
                operation = operation.next;
            } else {
//                这个条件不会成立，因为base先走到尾节点的前提是operation的第一个节点（最小的）都小于base，显然与上面选取base的逻辑冲突
//                if (base.next == null) {
//                    base.next = operation;
//                    break;
//                }
                base = base.next;
            }
        }
        return result;
    }
}
