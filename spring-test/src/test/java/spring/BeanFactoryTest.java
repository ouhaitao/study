package spring;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import spring.MyBean;

/**
 * @author parry
 * @date 2020/09/01
 */
public class BeanFactoryTest {
    
    @Test
    public void xmlBeanFactoryTest() {
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        new XmlBeanDefinitionReader(factory).loadBeanDefinitions(new ClassPathResource("bean.xml"));
//        factory.addBeanPostProcessor(factory.getBean(spring.BeanFactoryTest.postProcessor.class));
        MyBean myBean = (MyBean) factory.getBean("myBean");
        Assert.assertEquals("myBean", myBean.getName());
    }
    
    public static class postProcessor implements BeanPostProcessor {
        @Override
        public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
            System.out.println("before");
            return bean;
        }
    
        @Override
        public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
            System.out.println("after");
            return bean;
        }
    }
}
