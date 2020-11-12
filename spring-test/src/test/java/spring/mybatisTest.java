package spring;

import mybatis.TestMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author parry
 * @date 2020/10/12
 */
public class mybatisTest {
    
    @org.junit.Test
    public void test() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("datasourceConfig.xml");
        TestMapper testMapper = applicationContext.getBean(TestMapper.class);
        System.out.println(testMapper.test("1"));
    }
}
