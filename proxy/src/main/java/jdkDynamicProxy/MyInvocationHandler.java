package jdkDynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyInvocationHandler implements InvocationHandler {

    private Object target;

    private Advice advice;

    public MyInvocationHandler(Object target, Advice advice) {
        this.target = target;
        this.advice = advice;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        try {
            advice.getClass().getDeclaredMethod("before").invoke(advice);
        }catch (NoSuchMethodException e){
            System.out.println("there is not before method");
        }

        method.invoke(target,args);

        try {
            advice.getClass().getDeclaredMethod("after").invoke(advice);
        }catch (NoSuchMethodException e){
            System.out.println("there is not after method");
        }

        return null;
    }

}
