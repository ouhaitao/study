package 树.B树;

import java.util.ArrayList;
import java.util.List;

/**
 * m为B树的阶
 * 每个节点最多有m个孩子。
 * 每个内部节点（除去叶节点和根节点）至少有⌈m/2⌉（向上取整）孩子。
 * 如果根不是叶节点，则根至少有两个孩子。
 * 所有叶子都出现在同一层。
 * 具有k个孩子的非叶节点包含k-1个键，当然节点内的键也是递增的。（其中 m/2 <= k <= m）
 *
 * @author parry
 * @date 2019/12/24
 * @deprecated 使用BTree创建B树，该类仅作为实验类
 */
@Deprecated
public class TreeNode<T extends Comparable<T>> {
    
    /**
     * 节点中的值
     */
    private List<Entry<T>> entries;
    /**
     * 子节点
     */
    private List<TreeNode<T>> children;
    
    private int m;
    
    public TreeNode(int m) {
        this.m = m;
        // entries会浪费一个位置，因为最多只能存储m-1一个建，但是这样会让插入操作更方便
        entries = new ArrayList<>(m);
        children = new ArrayList<>(m);
    }
    
    private TreeNode(List<Entry<T>> entries, int m) {
        this.entries = entries;
        this.children = new ArrayList<>(m);
        this.m = m;
    }
    
    public T find(T value) {
        Entry<T> result = internalFind(new Entry<>(value));
        return result == null ? null : result.getValue();
    }
    
    private Entry<T> internalFind(Entry<T> entry) {
        Entry<T> result = null;
        if (this.entries.isEmpty()) {
            return null;
        }
        int i = 0;
        for (; i < this.entries.size(); i++) {
            Entry<T> tEntry = this.entries.get(i);
            int cResult = tEntry.compareTo(entry);
            if (cResult == 0) {
                // 相等
                result = tEntry;
                break;
            } else if (cResult > 0) {
                // entry小于当前节点
                result = this.children.get(i).internalFind(entry);
                break;
            }
        }
        // 比当前树节点的所有值都要大
        if (i == this.entries.size()) {
            result = this.children.get(i).internalFind(entry);
        }
        return result;
    }
    
    public TreeNode<T> add(T value) {
        return internalAdd(new Entry<>(value), null);
    }
    
    private TreeNode<T> internalAdd(Entry<T> entry, TreeNode<T> fatherNode) {
        boolean isLeaf = true;
        // 寻找entry插入的位置
        // 子节点不为空，即非叶子节点
        if (!checkChildrenEmpty()) {
            isLeaf = false;
            if (this.entries.get(0).compareTo(entry) > 0) {
                // 小于最小的值
                this.children.get(0).internalAdd(entry, this);
            } else if (this.entries.get(this.entries.size() - 1).compareTo(entry) < 0) {
                // 大于最大的数据
                this.children.get(this.entries.size()).internalAdd(entry, this);
            } else {
                for (int i = 1; i < this.entries.size(); i++) {
                    int result1 = this.entries.get(i - 1).compareTo(entry);
                    int result2 = this.entries.get(i).compareTo(entry);
                    if (result1 < 0 && result2 > 0) {
                        this.children.get(i).internalAdd(entry, this);
                    }
                }
            }
        }
        // 如果是叶子结点则插入
        if (isLeaf) {
            addEntry(entry);
        }
        if (checkEntriesFilled()) {
            // 是根节点
            if (fatherNode == null) {
                TreeNode<T> newHead = new TreeNode<>(this.m);
                newHead.addEntry(this.entries.get(m / 2));
                TreeNode<T> leftChildren = new TreeNode<>(createEntryList(0, m / 2), this.m);
                TreeNode<T> rightChildren = new TreeNode<>(createEntryList(m / 2 + 1, this.entries.size()), this.m);
                newHead.children.add(leftChildren);
                newHead.children.add(rightChildren);
                for (int i = 0; i < this.children.size(); i++) {
                    if (i <= m / 2) {
                        leftChildren.children.add(this.children.get(i));
                    } else {
                        rightChildren.children.add(this.children.get(i));
                    }
                }
                return newHead;
            } else {
                splitEntries(fatherNode);
            }
        }
        if (fatherNode == null) {
            return this;
        }
        return fatherNode;
    }
    
    private void splitEntries(TreeNode<T> fatherNode) {
        if (!checkEntriesFilled()) {
            return;
        }
        int i = fatherNode.addEntry(this.entries.get(m / 2));
        fatherNode.removeChildren(i);
        fatherNode.addChildren(createEntryList(0, m / 2), i);
        fatherNode.addChildren(createEntryList(m / 2 + 1, m), i + 1);
    }
    
    private boolean checkEntriesFilled() {
        return this.entries.size() == m;
    }
    
    private int addEntry(Entry<T> value) {
        int i = this.entries.size() - 1;
        this.entries.add(null);
        while (i >= 0 && (value.compareTo(this.entries.get(i)) < 0)) {
            this.entries.set(i + 1, this.entries.get(i));
            i--;
        }
        this.entries.set(++i, value);
        return i;
    }
    
    private void addChildren(List<Entry<T>> children, int i) {
        this.children.add(i, new TreeNode<>(children, this.m));
    }
    
    private boolean checkChildrenEmpty() {
        return this.children.isEmpty();
    }
    
    /**
     * 包含fromIndex，不包含toIndex
     */
    private List<Entry<T>> createEntryList(int fromIndex, int toIndex) {
        if (fromIndex == toIndex) {
            throw new IllegalArgumentException("fromIndex can not equal toIndex");
        }
        List<Entry<T>> list = new ArrayList<>();
        for (int i = fromIndex; i < toIndex; i++) {
            list.add(this.entries.get(i));
        }
        return list;
    }
    
    private void removeChildren(int i) {
        this.children.remove(i);
    }
}
