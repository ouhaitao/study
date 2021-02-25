package spi.java;

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
