package spring;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring.AopTest;
import spring.MyBean;

/**
 * @author parry
 * @date 2020/09/10
 */
public class ApplicationContextTest {
    
    @Test
    public void ClassPathXmlApplicationContextTest() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        MyBean bean = (MyBean) context.getBean("myBean");
        Assert.assertEquals("myBean", bean.getName());
        ((AopTest) context.getBean("spring.AopTest")).say();
        System.out.println("=======================");
        ((AopTest) context.getBean("spring.AopTest")).testSay();
    }
}
