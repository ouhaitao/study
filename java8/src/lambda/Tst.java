package lambda;

//这是一个函数式接口,该接口只能有一个抽象方法且该方法返回类型为void(Object类中的方法除外)
@FunctionalInterface
public interface Tst{
	
	public void test();

	//默认方法,即已经在接口中实现的方法
	default void t(int a){
		System.out.println(a);
	}

	@Override
    String toString();


	public static void main(String argv[]) {
		for (float y = (float) 1.5; y > -1.5; y -= 0.1) {
			for (float x = (float) -1.5; x < 1.5; x += 0.05) {
				float a = x * x + y * y - 1;
				if ((a * a * a - x * x * y * y * y) <= 0.0) {
					System.out.print("*");
				} else {
					System.out.print(" ");
				}
			}
			System.out.print("\n");
		}
	}
}
