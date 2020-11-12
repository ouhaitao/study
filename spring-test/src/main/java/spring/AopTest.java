package spring;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.aop.framework.AopContext;

import java.util.Random;

/**
 * @author parry
 * @date 2020/09/21
 */

public class AopTest {
    
    @org.aspectj.lang.annotation.Aspect
    static class Aspect {
        
        @Pointcut("execution(* *.say(..))")
        public void pointCut(){}
        
        @Before("pointCut()")
        public void before() {
            System.out.println("Aspect before");
        }
    
        @After("pointCut()")
        public void after() {
            System.out.println("Aspect after");
        }
    }
    
    public void say() {
        System.out.println("say hello");
    }
    
    public void testSay() {
//        获取代理类
//        需要开启expose-proxy="true"
        ((AopTest) AopContext.currentProxy()).say();
    }
}
