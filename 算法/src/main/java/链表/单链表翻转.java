package 链表;

/**
 * @author parry
 * @date 2020/04/13
 */
public class 单链表翻转 {
    
    public static void main(String[] args) {
        System.out.println(flip(Node.build(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9})));
    }
    
    public static Node flip(Node head) {
        if (head == null) {
            return null;
        }
        Node tmp = head, tmpNext = tmp.next;
        while (tmpNext != null) {
            Node tmpNextNext = tmpNext.next;
            tmpNext.next = tmp;
            if (tmp == head) {
                tmp.next = null;
            }
            tmp = tmpNext;
            tmpNext = tmpNextNext;
        }
        return tmp;
    }
    
}
