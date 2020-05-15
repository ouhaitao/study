package jdkDynamicProxy;

import java.lang.reflect.Proxy;

public class Main {

    public static void main(String[] args) {
        ServiceImpl service=new ServiceImpl();
        MyInvocationHandler myInvocationHandler=new MyInvocationHandler(service,new AdviceImpl());

        Service service1= (Service) Proxy.newProxyInstance(
                service.getClass().getClassLoader(),//委托类的类加载器
                service.getClass().getInterfaces(),//委托类的接口
                myInvocationHandler);//InvocationHandler

        service1.doService();

        System.out.println("实际类型:"+service1.getClass());
    }
    /**
     * jdk动态代理优势:加载速度快,因为他不需要像cglib那样重新生成代理类的字节码,可以通过反射调用委托类方法
     *      缺点:执行效率低,jdk代理通过反射执行,而cglib的代理类可直接调用委托类方法
     */
}
