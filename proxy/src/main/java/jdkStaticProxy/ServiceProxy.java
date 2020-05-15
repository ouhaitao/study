package jdkStaticProxy;

public class ServiceProxy implements Service {

    private Service service;

    public ServiceProxy(Service service) {
        this.service = service;
    }

    @Override
    public void doService() {
        System.out.println("there is before method");
        service.doService();
        System.out.println("there is after method");
    }
}
