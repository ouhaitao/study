package jdkStaticProxy;

/**
 *  要使用jdk代理必须实现一个接口
 *  原因是jdk代理是用代理类替换真实类进行方法调用,为了让代理能够完成替换,就必须实现一个接口,这样jdk就能生成对应的代理类
 */

public class ServiceImpl implements Service {

    @Override
    public void doService() {
        System.out.println("this is doService method");
    }

}
