package jdkStaticProxy;

public class Main {

    public static void main(String[] args) {
        Service service=new ServiceImpl();
        Service serviceProxy=new ServiceProxy(service);
        serviceProxy.doService();
    }

}
