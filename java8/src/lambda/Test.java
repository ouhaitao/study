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
		//()��ʾ���캯���еĽӿ�,�ýӿ�ֻ����һ�����󷽷��Ҹ÷�����������Ϊvoid(Object���еķ�������)
		new Thread(()-> System.out.println());
		new Test(()-> System.out.println());

		List<Double> l= Arrays.asList(1.0,2.0,3.0);
		//ÿ��ѭ������һ��Test����,����l��ǰ��������Ԫ�ظ�ֵ���ö���
		l.forEach(Test::new);
		//����System.out.println(x);xΪl��Ԫ��
		System.out.println(1);
		l.forEach(System.out::println);

		//��pִ�в���:�ж�һ��double����ֵ�Ƿ����1
		//predicate:����
		System.out.println(2);
		Predicate<Double> p= d->d.doubleValue()>1;
		l.forEach(x->{
			if(p.test(x))
				System.out.println(x);
		});
	}
}