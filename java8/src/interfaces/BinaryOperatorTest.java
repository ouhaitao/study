package interfaces;

import java.util.Comparator;
import java.util.function.BinaryOperator;

public class BinaryOperatorTest {
    
    public static void main(String[] args) {
        //�ýӿ���Ҫ�������,�ýӿ���BiFuncation������
        BinaryOperator<Integer> bin = (a, b) -> a + b;
        System.out.println(bin.apply(1, 2));
        
        //�������ֲ������
        Comparator<Integer> c = (a, b) -> a > b ? 1 : -1;
        BinaryOperator<Integer> bi = (a, b) -> c.compare(a, b) >= 0 ? a : b;
        System.out.println(bi.apply(1, 2));
        
        System.out.println(BinaryOperator.maxBy(c).apply(1, 2));
    }
}
