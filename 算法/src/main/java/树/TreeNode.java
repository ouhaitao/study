package 树;

import lombok.Getter;
import lombok.Setter;

/**
 * @author parry
 * @date 2020/04/28
 */
@Getter
@Setter
public class TreeNode {
    
    private int val;
    private TreeNode left;
    private TreeNode right;
    
    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
