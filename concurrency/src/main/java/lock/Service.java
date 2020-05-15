package lock;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Service implements Runnable {

    private Lock lock=new ReentrantLock();

    @Override
    public void run() {
        lock.lock();
        System.out.println("进入临界资源");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("准备提出临界资源");
        lock.unlock();
    }

    public static void main(String[] args) {
        Service service=new Service();
        new Thread(service).start();
        new Thread(service).start();
        Thread t=new Thread(service);
        t.start();
        t.interrupt();
    }
}
