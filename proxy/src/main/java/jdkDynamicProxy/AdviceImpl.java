package jdkDynamicProxy;

public class AdviceImpl implements Advice {


    @Override
    public void before() {
        System.out.println("----before-----");
    }

    @Override
    public void after() {
        System.out.println("----after-----");
    }
}
