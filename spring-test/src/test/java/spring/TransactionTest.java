package spring;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author parry
 * @date 2020/10/14
 */
public class TransactionTest {
    
    @Test
    public void transactionTest() {
        ApplicationContext context = new ClassPathXmlApplicationContext("datasourceConfig.xml");
        MyService service = (MyService) context.getBean("myService");
        System.out.println(service.test("2"));
    }
}
