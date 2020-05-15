package interfaces;

import java.util.function.BiFunction;
import java.util.function.Function;

public class BiFuncationTest {

	//BiFuncation接口需要两个入参
	public int a(int i, int j, BiFunction<Integer, Integer, Integer> a, Function<Integer, Integer> b){
		//下面两种方法结果相同
		System.out.println(b.apply(a.apply(i, j)));
		return a.andThen(b).apply(i, j);
	}
	
	public static void main(String[] args) {

		System.out.println(new BiFuncationTest().a(1, 2, (i, j)->i*j, i->i*i));
	}
}
