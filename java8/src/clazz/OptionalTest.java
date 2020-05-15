package clazz;

import java.util.Optional;

public class OptionalTest {

	public static void main(String[] args) {
		//����Optional�����ַ�ʽ
		Optional<String> o;
		o= Optional.empty();
		o= Optional.of("1");
		o= Optional.ofNullable("1");
		//���ֵ
		System.out.println(o.get());
		//�ж�ֵ�Ƿ�Ϊnull
		System.out.println(o.isPresent());
		//�ж�ֵ�Ƿ�Ϊnull,�����Ϊnull����в��������Ĳ���
		o.ifPresent(x-> System.out.println(x));
		
		System.out.println(o.filter(x->x.equals("1")));
		
		System.out.println(o.map(a->a+1).get());
		//�ж��Լ���ֵ�Ƿ�Ϊnull,���򷵻ظ����Ĳ�����,���򷵻��Լ���ֵ
		System.out.println(o.orElse("2"));
		//�ж��Լ���ֵ�Ƿ�Ϊnull,����ִ�и�����supplier������get����,���򷵻��Լ���ֵ
		System.out.println(o.orElseGet(()->"2"));
	}

}
