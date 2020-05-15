package 树.B树;

import lombok.Getter;

/**
 * @author parry
 * @date 2019/12/24
 * @deprecated 使用BTree创建B树，该类仅作为实验类
 */
@Getter
@Deprecated
public class Entry<T extends Comparable<T>> implements Comparable<Entry<T>> {
    
    public Entry(T value) {
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
