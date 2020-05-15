package atomicClass;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author parry
 * @date 2019/07/23
 */
public class AtomicIntegerExample implements Runnable {
    
    private static AtomicInteger atomicInteger = new AtomicInteger();
    private static CyclicBarrier barrier = new CyclicBarrier(2);
    private Integer a;
    
    private AtomicIntegerExample(Integer a) {
        this.a = a;
    }
    
    public static void main(String[] args) {
        AtomicIntegerExample example1 = new AtomicIntegerExample(1);
        AtomicIntegerExample example2 = new AtomicIntegerExample(2);
        int b = 0;
        int count = 0;
        while (count < 10) {
            Thread thread1 = new Thread(example1);
            Thread thread2 = new Thread(example2);
            thread1.start();
            thread2.start();
            try {
                thread1.join();
                thread2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (b != atomicInteger.get()) {
                System.out.print(atomicInteger+" ");
                b = atomicInteger.get();
            }
            count++;
            barrier.reset();
        }
    }
    
    @Override
    public void run() {
        try {
            barrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        //线程不安全
        atomicInteger.set(a);
    }
}
