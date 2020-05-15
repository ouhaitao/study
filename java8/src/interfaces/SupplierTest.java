package interfaces;

import java.util.function.Supplier;

public class SupplierTest {

	//该接口只有一个get方法,用于获取结果
	public static void main(String[] args) {
		//这里get方法返回的是1
		Supplier<Integer> s=()->1;
		System.out.println(s.get());
		//这里获取一个supplierTest类实例
		Supplier<SupplierTest> ss= SupplierTest::new;
		System.out.println(ss.get());
	}

	@Override
	public String toString() {
		return "supplierTest";
	}
}
