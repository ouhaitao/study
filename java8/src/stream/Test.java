package stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Test {

    //一个stream只能被操作一次,否则会抛出异常
    public static void main(String[] args) {
        List<Double> l = Arrays.asList(1.0, 2.0, 3.0);
        // 构造l的一个流,调用其map方法,表示对流里面的所有元素*1.5,然后遍历
        //map方法的参数是一个Funcation函数式接口,用lambda表达式创建该接口
//		l.stream().map(x -> x * 1.5).forEach(x -> System.out.println(x));

        // 构造l的一个流,调用map方法,表示对流里面的所有元素*1.5,然后将里面的所有元素想加
        //reduce方法的参数是一个BinaryOperator,一个函数式接口,用lambada创建该接口然后调用该操作,在源码里面有相似实现
//		System.out.println(l.stream().map(x -> x * 1.5).reduce((y, x) -> y + x).get());

        // 构造l的一个流,电泳filter方法,将所有大于2的元素过滤出来,然后组成一个list,然后遍历
        //filter方法的参数是一个predicate函数式接口,用lambda表达式创建一个predicate对象
//		l.stream().filter(x -> x > 2).collect(Collectors.toList()).forEach(x -> System.out.println(x));

        //构造一个ArrayList
//        l.stream().collect(Collectors.toCollection(ArrayList::new));
        // 构造一个流
//		Stream<String> s = Stream.of("1", "2", "32");
        // 将流中元素值大于2的过滤出来组成一个list,然后输出
//		s.filter(x -> Integer.parseInt(x) > 2).collect(Collectors.toList()).forEach(x -> System.out.println(x));
//		System.out.println(Stream.of("1","22","333").mapToInt(x->Integer.parseInt(x)).sum());

        //作用与sql中的distinct类似,去除重复元素,使用equal方法判断是否相同
//        String[] strArr = {"hello", "world"};
//        List<String[]> list = Stream.of(strArr)
//                .map(x->x.split(""))
//                .collect(Collectors.toList());
//        list.forEach(x->Stream.of(x).distinct().forEach(z-> System.out.print(z+" ")));
//
//        System.out.println();
//
//        //flatMap将字符串数组流扁平化
//        //根据输出结果可以得出结果,可以将扁平化理解为将数组元素合并成为一个字符串
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

        //peek方法存在的意义是用于debug,方便知道流处理中的元素变化
//        Stream.of("one", "two", "three", "four")
//                         .filter(e -> e.length() > 3)
//                         .peek(e -> System.ojut.println("Filtered value: " + e))
//                         .map(String::toUpperCase)
//                         .peek(e -> System.out.println("Mapped value: " + e))
//                         .collect(Collectors.toList());
        //流中元素的个数
//        Stream.of("one", "two", "three", "four")
//                .limit(3)
//                .forEach(x-> System.out.println(x));
        IntStream.iterate(3, x->x-1).limit(3).forEach(System.out::println);
    }
}
