package spi.dubbo;

/**
 * @author parry
 * @date 2020/12/30
 */
public class ServiceImpl implements IService {
    
    @Override
    public void sayHello() {
        System.out.println("hello");
    }
 
}
