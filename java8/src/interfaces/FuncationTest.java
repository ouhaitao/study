package interfaces;

import java.util.function.Function;

//Funcation接口需要1个入参
public class FuncationTest {

	//andThen(b)方法是在当前方法之后执行b操作
	public Integer a(int i, Function<Integer, Integer> a, Function<Integer, Integer> b){
		//下面两种操作相等
		System.out.println(b.apply(a.apply(i)));
		return a.andThen(b).apply(i);
	}
	//compose(b)方法是在当前方法之前执行b操作
	public Integer b(int i, Function<Integer, Integer> a, Function<Integer, Integer> b){
		//下面两种操作相等
		System.out.println(a.apply(b.apply(i)));
		return a.compose(b).apply(i);
	}
	
	public static void main(String[] args) {
		FuncationTest t=new FuncationTest();
		System.out.println(t.a(5, x->x*2, x->x*x));//100
		System.out.println(t.b(5, x->x*2, x->x*x));//50
	}
}
