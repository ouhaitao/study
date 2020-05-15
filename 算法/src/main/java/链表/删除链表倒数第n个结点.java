package 链表;

/**
 * @author parry
 * @date 2020/04/14
 */
public class 删除链表倒数第n个结点 {
    
    public static void main(String[] args) {
    
        System.out.println(fun(Node.build(new Integer[]{1,2,3,4,5}), 2));
    }
 
    private static Node fun(Node head, int n) {
        Node tmp = head, tmpNext = tmp, tmpPre = null;
        for (int i = 0; i < n; i++) {
            tmpNext = tmpNext.next;
        }
        do {
            if (tmpNext == null) {
                if (tmpPre == null) {
                    head = tmp.next;
                    break;
                }else {
                    tmpPre.next = tmp.next;
                    break;
                }
            }
            tmpPre = tmp;
            tmp = tmp.next;
            tmpNext = tmpNext.next;
        }while (tmp != null);
        return head;
    }
}
