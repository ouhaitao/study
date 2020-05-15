package 树.二叉树;

import lombok.Getter;
import lombok.Setter;

/**
 * @author parry
 * @date 2020/04/28
 */
@Getter
@Setter
public class Node {
    
    private int val;
    private Node left;
    private Node right;
    
    public Node(int val, Node left, Node right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
