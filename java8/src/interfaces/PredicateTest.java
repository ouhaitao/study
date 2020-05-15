package interfaces;

import java.util.function.Predicate;
import java.util.stream.Stream;

public class PredicateTest {

	public static void main(String[] args) {
		//该接口需要1个入参
		Predicate<Integer> p1= x->x>5;
		Predicate<Integer> p2= x->x<10;
		int a=6;
		//输出6
		System.out.println(Stream.of(a).filter(p1).findFirst().get());
		//输出6
		System.out.println(Stream.of(a).filter(p1.and(p2)).findFirst().get());
		//输出6
		System.out.println(Stream.of(a).filter(p1.or(p2)).findAny().get());
		
		//报错,因为流中不存在任何元素
		//System.out.println(Stream.of(a).filter(p1.negate()).findFirst().get());

		//下面两个操作相等
		System.out.println(Predicate.isEqual("t").test("t"));
		
		String s="t";
		Predicate<String> p3= o->s.equals(s);
		System.out.println(p3.test(s));
	}
}
