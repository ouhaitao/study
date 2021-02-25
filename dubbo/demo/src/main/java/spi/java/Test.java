package spi.java;

import java.util.ServiceLoader;

/**
 * @author parry
 * @date 2020/12/30
 */
public class Test {
    
    public static void main(String[] args) {
        ServiceLoader<IService> services = ServiceLoader.load(IService.class);
        for (IService service : services) {
            service.sayHello();
        }
    }
}
