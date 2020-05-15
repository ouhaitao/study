package lambda;

import java.util.Arrays;
import java.util.List;

public class MethodReference {

	static class Student{
		private String name;
		private int age;
		
		public Student(String name, int age) {
			this.name=name;
			this.age=age;
		}
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public int getAge() {
			return age;
		}
		public void setAge(int age) {
			this.age = age;
		}
		
		public static int compare(Student s1,Student s2){
            System.out.println(s1.name+" "+s2.name);
			return s1.getAge()-s2.getAge();
		}

		public int compares(Student s){
            System.out.println(this.name+" "+s.name);
			return this.age-s.getAge();
		}
		
		@Override
		public String toString() {
			return "Student [name=" + name + ", age=" + age + "]";
		}
		
	}
	
	static class StudentComparator{
		public int compare(Student s1,Student s2){
            System.out.println(s1.name+" "+s2.name);
			return s1.getAge()-s2.getAge();
		}
	}
	
	public static void main(String[] args) {
		List<Student> l= Arrays.asList(new Student("3",3),new Student("1",1),new Student("2",2));
		//l.sort((s1,s2)->s1.getAge()-s2.getAge());//lambda表达式实现comparator接口
		
//		l.sort(Student::compare);//类名::静态方法名,compare方法返回一个int,两个入参,跟comparator接口一样
//		l.sort(new StudentComparator()::compare);//对象名::实例方法名,compare方法返回一个int,两个入参,跟comparator接口一样
		//类名::实例方法名,compares方法返回一个int,只有一个入参,根comparator接口的compare方法不一样,为什么可以使用?
		/*这就是 类名::实例方法名 这种方法引用的特殊之处：当使用 类名::实例方法名 方法引用时，一定是lambda表达式所接收的
		第一个参数来调用实例方法，如果lambda表达式接收多个参数，其余的参数作为方法的参数传递进去。*/
		l.sort(Student::compares);
		
		l.forEach(x-> System.out.println(x));
		
		//ps:不允许重载,不然lambda表达式无法识别
	}
}
