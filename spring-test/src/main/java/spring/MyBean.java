package spring;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author parry
 * @date 2020/09/01
 */
@Getter
@Setter
@NoArgsConstructor
public class MyBean {
    
    private MyInterfaceImpl myInterface;
    
    private String name;
    
    /**
     * 如果MyInterfaceImpl使用的是jdk代理,这里会报错,因为JDK代理生成的是一个MyInterface的实现类,
     * 并不能转换成MyInterfaceImpl类型
     * CGlib是生成的MyInterfaceImpl的子类,所以可以成功注入
     */
    public MyBean(MyInterfaceImpl myInterface) {
        this.myInterface = myInterface;
    }
    
    static class MyInterfaceImpl implements MyInterface {
        
        @Override
        public void say() {
            System.out.println("hello");
        }
    }
    
}
