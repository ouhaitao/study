package interfaces;

import java.util.function.Supplier;

public class SupplierTest {

	//�ýӿ�ֻ��һ��get����,���ڻ�ȡ���
	public static void main(String[] args) {
		//����get�������ص���1
		Supplier<Integer> s=()->1;
		System.out.println(s.get());
		//�����ȡһ��supplierTest��ʵ��
		Supplier<SupplierTest> ss= SupplierTest::new;
		System.out.println(ss.get());
	}

	@Override
	public String toString() {
		return "supplierTest";
	}
}
