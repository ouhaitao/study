package clazz;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class OptionalTest {

	public static void main(String[] args) {
		//创建Optional的三种方式
		Optional<String> o;
		o= Optional.empty();
		o= Optional.of("1");
		o= Optional.ofNullable("1");
		//获得值
		System.out.println(o.get());
		//判断值是否为null
		System.out.println(o.isPresent());
		//判断值是否为null,如果不为null则进行参数给出的操作
		o.ifPresent(x-> System.out.println(x));
		
		System.out.println(o.filter(x->x.equals("1")));
		
		System.out.println(o.map(a->a+1).get());
		//判断自己的值是否为null,是则返回给出的参数的,否则返回自己的值
		System.out.println(o.orElse("2"));
		//判断自己的值是否为null,是则执行给出的supplier参数的get方法,否则返回自己的值
		System.out.println(o.orElseGet(()->"2"));
		
		Optional<List<Integer>> optional = Optional.of(Arrays.asList(1, 2, 3, 4, 5, 6, 7));
		optional.ifPresent(list -> System.out.println(list.stream().filter(x->x>5).collect(Collectors.toList())));
	}

}
