package main;

import threadLocal.ThreadLocalService;

public class Main {

    
    public static void main(String[] args) {
        serviceTest();
    }

    public static void serviceTest(){
        Service s1=new Service();
        new Thread(s1).start();
        new Thread(s1).start();
    }

    public static void ThreadLocalServiceTest(){
        ThreadLocalService s1=new ThreadLocalService();
        Thread t1=new Thread(s1);
        Thread t2=new Thread(s1);
        t1.start();
        t2.start();
    }

    public static void InheritableThreadLocalTest(){
        ThreadLocal inheritableThreadLocal=new InheritableThreadLocal();
        int a=1;
        inheritableThreadLocal.set(a);
        new Thread(()-> System.out.println(inheritableThreadLocal.get())).start();
    }


    
}
