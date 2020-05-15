package stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Test {

    //һ��streamֻ�ܱ�����һ��,������׳��쳣
    public static void main(String[] args) {
        List<Double> l = Arrays.asList(1.0, 2.0, 3.0);
        // ����l��һ����,������map����,��ʾ�������������Ԫ��*1.5,Ȼ�����
        //map�����Ĳ�����һ��Funcation����ʽ�ӿ�,��lambda���ʽ�����ýӿ�
//		l.stream().map(x -> x * 1.5).forEach(x -> System.out.println(x));

        // ����l��һ����,����map����,��ʾ�������������Ԫ��*1.5,Ȼ�����������Ԫ�����
        //reduce�����Ĳ�����һ��BinaryOperator,һ������ʽ�ӿ�,��lambada�����ýӿ�Ȼ����øò���,��Դ������������ʵ��
//		System.out.println(l.stream().map(x -> x * 1.5).reduce((y, x) -> y + x).get());

        // ����l��һ����,��Ӿfilter����,�����д���2��Ԫ�ع��˳���,Ȼ�����һ��list,Ȼ�����
        //filter�����Ĳ�����һ��predicate����ʽ�ӿ�,��lambda���ʽ����һ��predicate����
//		l.stream().filter(x -> x > 2).collect(Collectors.toList()).forEach(x -> System.out.println(x));

        //����һ��ArrayList
//        l.stream().collect(Collectors.toCollection(ArrayList::new));
        // ����һ����
//		Stream<String> s = Stream.of("1", "2", "32");
        // ������Ԫ��ֵ����2�Ĺ��˳������һ��list,Ȼ�����
//		s.filter(x -> Integer.parseInt(x) > 2).collect(Collectors.toList()).forEach(x -> System.out.println(x));
//		System.out.println(Stream.of("1","22","333").mapToInt(x->Integer.parseInt(x)).sum());

        //������sql�е�distinct����,ȥ���ظ�Ԫ��,ʹ��equal�����ж��Ƿ���ͬ
//        String[] strArr = {"hello", "world"};
//        List<String[]> list = Stream.of(strArr)
//                .map(x->x.split(""))
//                .collect(Collectors.toList());
//        list.forEach(x->Stream.of(x).distinct().forEach(z-> System.out.print(z+" ")));
//
//        System.out.println();
//
//        //flatMap���ַ�����������ƽ��
//        //�������������Եó����,���Խ���ƽ�����Ϊ������Ԫ�غϲ���Ϊһ���ַ���
//        List<String> list1=Stream.of(strArr)
//                .map(x->x.split(""))
//                .flatMap(Arrays::stream)
//                .distinct()
//                .collect(Collectors.toList());
//        list1.forEach(x-> System.out.print(x+" "));
//
//        System.out.println();
//
//        String[][] s={{"a","a"},{"a","a"}};
//        Stream.of(s).flatMap(Arrays::stream)
//                .distinct()
//                .collect(Collectors.toList())
//                .forEach(x-> System.out.print(x+" "));

        //peek�������ڵ�����������debug,����֪���������е�Ԫ�ر仯
//        Stream.of("one", "two", "three", "four")
//                         .filter(e -> e.length() > 3)
//                         .peek(e -> System.ojut.println("Filtered value: " + e))
//                         .map(String::toUpperCase)
//                         .peek(e -> System.out.println("Mapped value: " + e))
//                         .collect(Collectors.toList());
        //����Ԫ�صĸ���
//        Stream.of("one", "two", "three", "four")
//                .limit(3)
//                .forEach(x-> System.out.println(x));
        IntStream.iterate(3, x->x-1).limit(3).forEach(System.out::println);
    }
}
