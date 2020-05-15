package atomicClass;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * @author parry
 * @date 2019/07/25
 */
public class AtomicIntegerArrayExample {
    
    public static void main(String[] args) {
        AtomicIntegerArray array=new AtomicIntegerArray(4);
        array.set(0, 1);
        array.set(1, 2);
    }
}
