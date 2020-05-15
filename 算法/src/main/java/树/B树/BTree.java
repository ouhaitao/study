package 树.B树;

import lombok.Getter;

import java.util.ArrayList;
import java.util.LinkedList;
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
 */
public class BTree<T extends Comparable<T>> {
    
    private TreeNode<T> head;
    
    
    public BTree(int m) {
        if (m <= 2) {
            throw new IllegalArgumentException("the parameter must greater than 2");
        }
        head = new TreeNode<>(m);
    }
    
    public void add(T value) {
        head = head.add(value);
    }
    
    public T find(T value) {
        return head.find(value);
    }
    
    public T remove(T value) {
        return head.remove(value);
    }
    
    private static class TreeNode<T extends Comparable<T>> {
        
        /**
         * 节点中的值
         */
        private List<Entry<T>> entries;
        /**
         * 子节点
         */
        private List<TreeNode<T>> children;
        
        private int m;
        
        private TreeNode(int m) {
            this.m = m;
            // 由于在操作过程中使用到了多次在指定位置插入数据，所以采用LinkedList
            entries = new LinkedList<>();
            children = new LinkedList<>();
        }
        
        private TreeNode(List<Entry<T>> entries, int m) {
            this.entries = entries;
            this.children = new LinkedList<>();
            this.m = m;
        }
        
        private T find(T value) {
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
        
        private TreeNode<T> add(T value) {
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
                    // 新头结点
                    TreeNode<T> newHead = new TreeNode<>(this.m);
                    // 原头结点的m/2位置的节点上升至新头结点
                    newHead.addEntry(this.entries.get(m / 2));
                    // 心头节点的子字典必定是两个
                    // 原头结点中0 - m/2位置的元素是小于m/2位置的元素，m/2+1 - (size-1)位置的元素是大于m/2位置的元素
                    TreeNode<T> leftChildren = new TreeNode<>(createEntryList(0, m / 2), this.m);
                    TreeNode<T> rightChildren = new TreeNode<>(createEntryList(m / 2 + 1, this.entries.size()), this.m);
                    newHead.children.add(leftChildren);
                    newHead.children.add(rightChildren);
                    // 处理原头结点的子节点
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
        
        private T remove(T value) {
            Entry<T> result = internalRemove(new Entry<>(value), null, -1);
            return result == null ? null : result.getValue();
        }
        
        /**
         * 如果删除的值在叶子节点（不是根节点）上
         * 1. 直接删除
         * 2. 删除之后如果当前节点的值的个数大于等于m/2，则结束操作；否则，如果兄弟节点的值大于m/2，执行第三步，如果兄弟节点的值小于m/2，执行第四步。(实际上不会小于小于m/2)
         * 3. 如果兄弟节点的值的个数大于m/2，则将兄弟节点中的key上移，父节点的key下移，结束操作
         * 4. 如果兄弟节点的值的个数小于等于m/2，则将父节点的值下移一个，然后与一个兄弟节点合并
         * 5. 如果父节点的值的个数小于m/2，则递归的执行第4步，否则结束操作
         * <p>
         * 如果删除的值在非叶子节点上
         * 1. 将子节点中的一个值上升到当前节点
         * 2. 如果子节点还有子节点，则递归进行第一步，否则执行上述叶子节点的删除操作
         * <p>
         * 如果删除的值在根节点上
         * 1. 如果根节点没有子节点，则直接删除，结束操作
         * 2. 如果有子节点，且每个子节点的值的个数等于m/2，则合并子节点，结束操作，否则执行下一步
         * 3. 如果有子节点，且含有一个子节点的值大于m/2，则将子节点的值上升至根节点，结束操作
         *
         * @param value      要删除的值
         * @param fatherNode 父节点
         * @param index      当前节点在父节点的子节点数组中的下标
         * @return
         */
        private Entry<T> internalRemove(Entry<T> value, TreeNode<T> fatherNode, int index) {
            boolean findInThis = false;
            boolean findInChildren = false;
            Entry<T> remove = null;
            // 查找逻辑
            if (this.entries.isEmpty()) {
                return null;
            }
            int i = 0;
            // 由于删除过程会改变entries的大小，所以需要用变量保存
            int entriesSize = this.entries.size();
            for (; i < entriesSize; i++) {
                Entry<T> tEntry = this.entries.get(i);
                int cResult = tEntry.compareTo(value);
                if (cResult == 0) {
                    findInThis = true;
                    break;
                } else if (cResult > 0) {
                    // entry小于当前节点
                    remove = this.children.get(i).internalRemove(value, this, i);
                    findInChildren = remove != null;
                    break;
                }
            }
            // 比当前树节点的所有值都要大
            if (i == entriesSize) {
                remove = this.children.get(i).internalRemove(value, this, i);
                findInChildren = remove != null;
            }
            
            // 在当前节点找到
            if (findInThis) {
                // 如果是叶子结点就删除
                if (checkChildrenEmpty()) {
                    remove = this.entries.remove(i);
                } else {
                    TreeNode<T> childNode;
                    // 左子节点的值大于m/2,将子节点中的值上移
                    if ((childNode = this.children.get(i)).entries.size() > getEntriesMinCount()) {
                        remove = this.entries.set(i, childNode.entries.get(childNode.entries.size() - 1));
                        childNode.entries.remove(childNode.entries.size() - 1);
                    } else {
                        childNode = this.children.get(i + 1);
                        remove = this.entries.set(i, childNode.entries.get(0));
                        childNode.entries.remove(0);
                        // 子节点值上移之后其节点数可能会不符合规则
                        childNode.dealTreeStructure(this, i + 1);
                    }
                }
            } else if (findInChildren) { // 在子节点中找到，不作任何处理
            
            }
            if (remove != null) {
                dealTreeStructure(fatherNode, index);
            }
            return remove;
        }
        
        private void dealTreeStructure(TreeNode<T> fatherNode, int index) {
            if (this.entries.size() < getEntriesMinCount()) {
                if (index > 0 && fatherNode.children.get(index - 1).entries.size() > getEntriesMinCount()) {
                    // 左兄弟节点的值的个数大于m/2
                    moveBrotherNodeToThis(fatherNode, index - 1, index - 1, true);
                } else if (index < (fatherNode.children.size() - 1) && fatherNode.children.get(index + 1).entries.size() > getEntriesMinCount()) {
                    // 右兄弟节点的值的个数大于m/2
                    moveBrotherNodeToThis(fatherNode, index, index + 1, false);
                } else {
                    // 兄弟节点的值的个数都小于等于m/2
                    if (index == 0) {
                        moveFatherNodeToThis(fatherNode, 0, 1, index);
                    } else {
                        moveFatherNodeToThis(fatherNode, index - 1, index - 1, index);
                    }
                }
            }
        }
        
        /**
         * 将父节点值下移，然后与一个兄弟节点合并
         *
         * @param fatherNode   父节点
         * @param entryIndex   要下移的值的下标
         * @param brotherIndex 兄弟节点的下标
         */
        private void moveFatherNodeToThis(TreeNode<T> fatherNode, int entryIndex, int brotherIndex, int thisIndex) {
            // 下移
            this.entries.add(fatherNode.entries.get(entryIndex));
            if (thisIndex < brotherIndex) {
                // 与右兄弟节点合并
                this.entries.addAll(fatherNode.children.get(brotherIndex).entries);
            } else {
                // 与左兄弟节点合并
                this.entries.addAll(0, fatherNode.children.get(brotherIndex).entries);
            }
            fatherNode.entries.remove(entryIndex);
            fatherNode.children.remove(brotherIndex);
        }
        
        /**
         * 父节点值下移，兄弟节点值上移
         *
         * @param fatherNode       父节点
         * @param fatherEntryIndex 父节点下移值的下标
         * @param brotherNodeIndex 兄弟节点的下标
         * @param isLeftBrother    是否是左兄弟节点
         */
        private void moveBrotherNodeToThis(TreeNode<T> fatherNode, int fatherEntryIndex, int brotherNodeIndex, boolean isLeftBrother) {
            TreeNode<T> brotherNode = fatherNode.children.get(brotherNodeIndex);
            // 兄弟节点上移值的下标,当兄弟节点是当前节点的右兄弟节点时为0,为左兄弟节点时为brotherNode.entries.size - 1
            int brotherEntryIndex = isLeftBrother ? brotherNode.entries.size() - 1 : 0;
            // 父节点值下移
            this.addEntry(fatherNode.entries.get(fatherEntryIndex));
            // 兄弟节点值上移
            fatherNode.entries.set(fatherEntryIndex, brotherNode.entries.get(brotherEntryIndex));
            brotherNode.entries.remove(brotherEntryIndex);
            // 如果兄弟节点不是叶子结点
            // 父节点下移之后会导致当前节点增加一个子节点才能保持规则,兄弟节点上移之后需要减少一个子节点才能保持规则
            if (!brotherNode.checkChildrenEmpty()) {
                if (isLeftBrother) {
                    TreeNode<T> remove = brotherNode.children.remove(brotherNode.children.size() - 1);
                    this.children.add(0, remove);
                } else {
                    TreeNode<T> remove = brotherNode.children.remove(0);
                    this.children.add(remove);
                }
            }
        }
        
        private int getEntriesMinCount() {
            return m / 2;
        }
        
    }
    
    @Getter
    private static class Entry<T extends Comparable<T>> implements Comparable<Entry<T>> {
        
        private Entry(T value) {
            this.value = value;
        }
        
        private T value;
        
        @Override
        public int compareTo(Entry<T> o) {
            return value.compareTo(o.value);
        }
        
        @Override
        public String toString() {
            return value.toString();
        }
    }
    
}
