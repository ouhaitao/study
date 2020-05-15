package clazz;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CollectorsTest {

	public static void main(String[] args) {
		List<String> l= Arrays.asList("111","22","3","4");
		//groupingBy()����һ��Funcation���͵Ĳ���,����ָ����ʲô�����з���,���������ַ����ĳ��Ƚ��з���
		Map<Integer, List<String>> m = l.stream().collect(Collectors.groupingBy(String::length));
		//���ص�map��key�ǳ���,value�ǳ���Ϊ(key��ֵ)���ַ���
		System.out.println(m);
		//groupingBy()��һ����������������һ��,�ڶ���������ʾ�ڷ�����֮���øò���ָ�����ռ������д���
		Map<Integer, Long> mm = l.stream().collect(Collectors.groupingBy(String::length, Collectors.counting()));
		//�����map��key�ǳ���,value�ǳ���Ϊ(key��ֵ)���ַ���������
		System.out.println(mm);
		//����������������������ڸ÷�������ָ������ʲô����Map
		HashMap<Integer, Long> mmm = l.stream().collect(Collectors.groupingBy(String::length, HashMap::new, Collectors.counting()));
		System.out.println(mmm);
		//���ո�����Predicate���͵��������з���,���������Ĵ��keyΪtrue����,������keyΪfalse����
		Map<Boolean, List<String>> mmmm=l.stream().collect(Collectors.partitioningBy(x->x.length()>1));
		System.out.println(mmmm);
	}
}
