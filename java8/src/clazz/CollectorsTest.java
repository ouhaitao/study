package clazz;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CollectorsTest {

	public static void main(String[] args) {
		List<String> l= Arrays.asList("111","22","3","4");
		//groupingBy()接收一个Funcation类型的参数,用于指定用什么来进行分组,这里是用字符串的长度进行分组
		Map<Integer, List<String>> m = l.stream().collect(Collectors.groupingBy(String::length));
		//返回的map的key是长度,value是长度为(key的值)的字符串
		System.out.println(m);
		//groupingBy()第一个参数作用于上面一样,第二个参数表示在分组完之后用该参数指定的收集器进行处理
		Map<Integer, Long> mm = l.stream().collect(Collectors.groupingBy(String::length, Collectors.counting()));
		//这里的map的key是长度,value是长度为(key的值)的字符串的数量
		System.out.println(mm);
		//这里的区别跟上面的区别在于该方法可以指定生成什么样的Map
		HashMap<Integer, Long> mmm = l.stream().collect(Collectors.groupingBy(String::length, HashMap::new, Collectors.counting()));
		System.out.println(mmm);
		//按照给出的Predicate类型的条件进行分区,符合条件的存才key为true里面,否则存才key为false里面
		Map<Boolean, List<String>> mmmm=l.stream().collect(Collectors.partitioningBy(x->x.length()>1));
		System.out.println(mmmm);
	}
}
