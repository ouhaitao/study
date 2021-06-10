package 树.B树;

/**
 * @author parry
 * @date 2019/12/24
 */
public class Main {
    
    public static void main(String[] args) {
        Integer[] data = {9, 2, 6, 12, 1, 3, 5, 8, 11, 13, 15};
        BTree<Integer> head = buildTree(data);
        System.out.println(head.remove(2));
        System.out.println(head.remove(3));
        System.out.println(head.remove(1));
        System.out.println(head.remove(5));
        System.out.println();
    }
    
    public static BTree<Integer> buildTree(Integer[] data) {
        BTree<Integer> head = new BTree<>(4);
        for (Integer d : data) {
            head.add(d);
        }
        return head;
    }
}
