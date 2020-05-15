package interfaces;

import java.util.function.Predicate;
import java.util.stream.Stream;

public class PredicateTest {

	public static void main(String[] args) {
		//�ýӿ���Ҫ1�����
		Predicate<Integer> p1= x->x>5;
		Predicate<Integer> p2= x->x<10;
		int a=6;
		//���6
		System.out.println(Stream.of(a).filter(p1).findFirst().get());
		//���6
		System.out.println(Stream.of(a).filter(p1.and(p2)).findFirst().get());
		//���6
		System.out.println(Stream.of(a).filter(p1.or(p2)).findAny().get());
		
		//����,��Ϊ���в������κ�Ԫ��
		//System.out.println(Stream.of(a).filter(p1.negate()).findFirst().get());

		//���������������
		System.out.println(Predicate.isEqual("t").test("t"));
		
		String s="t";
		Predicate<String> p3= o->s.equals(s);
		System.out.println(p3.test(s));
	}
}
