package cglibProxy;

import net.sf.cglib.proxy.Enhancer;

public class Main {
    
    public static void main(String[] args) {
        
        Enhancer enhancer = new Enhancer();//该类是cglib的字节码生成器,能够直接生成对应代理类的字节码
        enhancer.setSuperclass(Service.class);//继承被代理类(final类不可被继承)
        enhancer.setCallback(new MyInterceptor());//设置回调
        Service service = (Service) enhancer.create();//生成代理对象
        
        service.doService();
        System.out.println("实际类型:" + service.getClass());
        
    }
    /**
     * 代理步骤
     * 1.生成代理类的二进制字节码文件；
     * 2.加载二进制字节码，生成Class对象( 例如使用Class.forName()方法 )；
     * 3.通过反射机制获得实例构造，并创建代理类对象
     *
     * cglib优势:执行效率高,因为他是直接生成对应类的字节码,相当于是直接生成了一个类可以直接调用委托类的方法,不需要像jdk代理那样通过反射调用
     *      缺点:因为他需要生成字节码,所以加载类相对较慢
     */
}
