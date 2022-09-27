package spi.java;

import java.util.ServiceLoader;

/**
 * @author parry
 * @date 2020/12/30
 */
public class Test {
    
    /**
     * 需要在classpath下的MATA-INF/service目录下写一个文件用于服务发现
     */
    public static void main(String[] args) {
        ServiceLoader<IService> services = ServiceLoader.load(IService.class);
        for (IService service : services) {
            service.sayHello();
        }
    }
}
