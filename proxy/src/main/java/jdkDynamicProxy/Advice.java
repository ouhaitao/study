package jdkDynamicProxy;

public interface Advice {

    default public void before(){}

    default public void after(){}
}
