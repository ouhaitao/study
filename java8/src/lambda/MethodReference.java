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
		//l.sort((s1,s2)->s1.getAge()-s2.getAge());//lambda���ʽʵ��comparator�ӿ�
		
//		l.sort(Student::compare);//����::��̬������,compare��������һ��int,�������,��comparator�ӿ�һ��
//		l.sort(new StudentComparator()::compare);//������::ʵ��������,compare��������һ��int,�������,��comparator�ӿ�һ��
		//����::ʵ��������,compares��������һ��int,ֻ��һ�����,��comparator�ӿڵ�compare������һ��,Ϊʲô����ʹ��?
		/*����� ����::ʵ�������� ���ַ������õ�����֮������ʹ�� ����::ʵ�������� ��������ʱ��һ����lambda���ʽ�����յ�
		��һ������������ʵ�����������lambda���ʽ���ն������������Ĳ�����Ϊ�����Ĳ������ݽ�ȥ��*/
		l.sort(Student::compares);
		
		l.forEach(x-> System.out.println(x));
		
		//ps:����������,��Ȼlambda���ʽ�޷�ʶ��
	}
}
