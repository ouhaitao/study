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
    
    public int val;
    public TreeNode left;
    public TreeNode right;
    
    public TreeNode(int val) {
        this.val = val;
    }
    
    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
