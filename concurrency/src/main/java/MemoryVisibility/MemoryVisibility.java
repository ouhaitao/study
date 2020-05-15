package MemoryVisibility;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author parry
 * @date 2019/09/06
 */
public class MemoryVisibility {
    
    private  int a = 1;
    private boolean flag = true;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    
    
    public static void main(String[] args) throws InterruptedException {
        MemoryVisibility memoryVisibility = new MemoryVisibility();
        memoryVisibility.unSafe();
    }
    
    private void unSafe() throws InterruptedException {
        Runnable unSafeRunnable1 = () -> {
            while (this.isFlag()) {}
            System.out.println("thread1 finished");
        };
        
        Runnable unSafeRunnable2 = () -> {
            this.setFlag(false);
            System.out.println("thread2 finished");
        };
        Thread thread1 = new Thread(unSafeRunnable1);
        Thread thread2 = new Thread(unSafeRunnable2);
        thread1.start();
        //让thread1先运行，由于线程在第一次使用共享变量的时候会从主存中读取，所以如果thread2先运行那么thread1获取到的flag是false
        Thread.sleep(500L);
        thread2.start();
//        该方法只需要将flag改成volatile变量就能够正常运行
    }
    
    private void safe1() throws InterruptedException {
        Runnable safeRunnable1 = () -> {
            synchronized (MemoryVisibility.class) {
                while (this.isFlag()) {
                    try {
                        MemoryVisibility.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("thread1 finished");
            }
        };
        
        Runnable safeRunnable2 = () -> {
            synchronized (MemoryVisibility.class) {
                this.setFlag(false);
                MemoryVisibility.class.notify();
            }
            System.out.println("thread2 finished");
        };
        Thread thread1 = new Thread(safeRunnable1);
        Thread thread2 = new Thread(safeRunnable2);
        thread1.start();
        Thread.sleep(1000L);
        thread2.start();
    }
    
    private void safe2() throws InterruptedException {
        Runnable safeRunnable1 = () -> {
            this.lock();
            while (this.isFlag()) {
                this.conditionAwait();
            }
            this.unLock();
            System.out.println("thread1 finished");
        };
        
        Runnable safeRunnable2 = () -> {
            this.lock();
            this.setFlag(false);
            this.conditionSignal();
            this.unLock();
            System.out.println("thread2 finished");
        };
        Thread thread1 = new Thread(safeRunnable1);
        Thread thread2 = new Thread(safeRunnable2);
        thread1.start();
        Thread.sleep(1000L);
        thread2.start();
    }
    
    private void safe3() throws InterruptedException {
        Runnable safeRunnable1 = () -> {
            while (this.isFlag()) {
                System.out.println(this.getA());
            }
            System.out.println("thread1 finished");
        };
        
        Runnable safeRunnable2 = () -> {
            this.setFlag(false);
            System.out.println("thread2 finished");
        };
        Thread thread1 = new Thread(safeRunnable1);
        Thread thread2 = new Thread(safeRunnable2);
        thread1.start();
        Thread.sleep(1000L);
        thread2.start();
    }
    
    private boolean isFlag() {
        return flag;
    }
    
    private void setFlag(boolean flag) {
        this.flag = flag;
    }
    
    private void lock() {
        lock.lock();
    }
    
    private void unLock() {
        lock.unlock();
    }
    
    private void conditionAwait() {
        try {
            condition.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    private void conditionSignal() {
        condition.signal();
    }
    
    private int getA() {
        return a;
    }
    
    private void setA(int a) {
        this.a = a;
    }
    
}
