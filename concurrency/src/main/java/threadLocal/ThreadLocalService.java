package threadLocal;

public class ThreadLocalService implements Runnable {
    
    private Integer a = 0;
    
    private ThreadLocal<String> threadLocal1;
    private ThreadLocal<String> threadLocal2;
    private ThreadLocal<String> threadLocal3;
    private ThreadLocal<String> threadLocal4;
    private ThreadLocal<String> threadLocal5;
    private ThreadLocal<String> threadLocal6;
    private ThreadLocal<String> threadLocal7;
    private ThreadLocal<String> threadLocal8;
    private ThreadLocal<String> threadLocal9;
    private ThreadLocal<String> threadLocal10;
    private ThreadLocal<String> threadLocal11;
    private ThreadLocal<String> threadLocal12;
    private ThreadLocal<String> threadLocal13;
    private ThreadLocal<String> threadLocal14;
    private ThreadLocal<String> threadLocal15;
    private ThreadLocal<String> threadLocal16;
    
    
    public ThreadLocalService() {
        threadLocal1 = new ThreadLocal<>();
        threadLocal2 = new ThreadLocal<>();
        threadLocal3 = new ThreadLocal<>();
        threadLocal4 = new ThreadLocal<>();
        threadLocal5 = new ThreadLocal<>();
        threadLocal6 = new ThreadLocal<>();
        threadLocal7 = new ThreadLocal<>();
        threadLocal8 = new ThreadLocal<>();
        threadLocal9 = new ThreadLocal<>();
        threadLocal10 = new ThreadLocal<>();
        threadLocal11 = new ThreadLocal<>();
        threadLocal12 = new ThreadLocal<>();
        threadLocal13 = new ThreadLocal<>();
        threadLocal14 = new ThreadLocal<>();
        threadLocal15 = new ThreadLocal<>();
        threadLocal16 = new ThreadLocal<>();
    }
    
    @Override
    public void run() {
        try {
            threadLocal1.set("threadLocal1");
            threadLocal2.set("threadLocal2");
            threadLocal3.set("threadLocal3");
            threadLocal4.set("threadLocal4");
            threadLocal5.set("threadLocal2");
            threadLocal6.set("threadLocal2");
            threadLocal7.set("threadLocal2");
            threadLocal8.set("threadLocal2");
            threadLocal9.set("threadLocal2");
            threadLocal10.set("threadLocal2");
            threadLocal11.set("threadLocal2");
            threadLocal12.set("threadLocal2");
            threadLocal13.set("threadLocal2");
            threadLocal14.set("threadLocal2");
            threadLocal15.set("threadLocal2");
            threadLocal16.set("threadLocal2");
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + " " + threadLocal1.get());
            System.out.println(Thread.currentThread().getName() + " " + threadLocal2.get());
            threadLocal1.remove();
            threadLocal2.remove();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    /**
     * 在Thread类中有一个threadLocals属性，该属性是ThreadLocal.ThreadLocalMap类，
     * 该类中有一个Entry内部类，该内部类是一个继承自弱引用的子类，有一个Object类的Value属性用于存储用户的值，
     * 该类可以看成是一个K-V对象，因为其继承自弱引用的原因，其本身就有一个referent属性作为key
     *
     * 在调用ThreadLocal的set方法时，实际上是调用ThreadLocal.ThreadLocalMap的set方法，
     * 首先获取当前线程的threadLocals，然后通过ThreadLocal类中的threadLocalHashCode属性和Entry数组的长度
     * 做逻辑与操作确定其值在Entry数组中的位置，然后更新值
     *
     * ps：值得注意的是ThreadLocal并不是线程私有变量，Entry是弱引用类型
     */
}
