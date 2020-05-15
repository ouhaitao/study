package lambda;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class Test{
	
	static int a=1;
	static int b=2;
	
	public Test(Tst t) {
	}
	
	public Test(Double a) {
	}
	
	public Test() {
	}
	
	public static void main(String[] args) throws InterruptedException, IOException {
		//()表示构造函数中的接口,该接口只能有一个抽象方法且该方法返回类型为void(Object类中的方法除外)
		new Thread(()-> System.out.println());
		new Test(()-> System.out.println());

		List<Double> l= Arrays.asList(1.0,2.0,3.0);
		//每次循环创建一个Test对象,并将l当前遍历到的元素赋值给该对象
		l.forEach(Test::new);
		//调用System.out.println(x);x为l的元素
		System.out.println(1);
		l.forEach(System.out::println);

		//让p执行操作:判断一个double类型值是否大于1
		//predicate:断言
		System.out.println(2);
		Predicate<Double> p= d->d.doubleValue()>1;
		l.forEach(x->{
			if(p.test(x))
				System.out.println(x);
		});
	}
}