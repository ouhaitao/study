package interfaces;

import java.util.function.Function;

//Funcation�ӿ���Ҫ1�����
public class FuncationTest {

	//andThen(b)�������ڵ�ǰ����֮��ִ��b����
	public Integer a(int i, Function<Integer, Integer> a, Function<Integer, Integer> b){
		//�������ֲ������
		System.out.println(b.apply(a.apply(i)));
		return a.andThen(b).apply(i);
	}
	//compose(b)�������ڵ�ǰ����֮ǰִ��b����
	public Integer b(int i, Function<Integer, Integer> a, Function<Integer, Integer> b){
		//�������ֲ������
		System.out.println(a.apply(b.apply(i)));
		return a.compose(b).apply(i);
	}
	
	public static void main(String[] args) {
		FuncationTest t=new FuncationTest();
		System.out.println(t.a(5, x->x*2, x->x*x));//100
		System.out.println(t.b(5, x->x*2, x->x*x));//50
	}
}
