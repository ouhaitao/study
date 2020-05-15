package lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ServiceCondition implements Runnable {

    private static Lock lock = new ReentrantLock();
    private static boolean flag = false;
    private static Condition condition = lock.newCondition();

    @Override
    public void run() {
        try {
            lock.lock();
            while (!flag) {
                System.out.println("条件为假,等待");
                //释放锁并将自己挂起在锁上
                System.gc();
                condition.await();
            }
            System.out.println("条件为真,执行");
            lock.unlock();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ServiceCondition serviceCondition=new ServiceCondition();
        new Thread(serviceCondition).start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        lock.lock();
        flag=true;
        condition.signal();
        lock.unlock();
    }
}
