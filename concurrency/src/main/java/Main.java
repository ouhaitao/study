import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author parry
 * @date 2019/09/18
 */
public class Main {
    
    private int num = 0;
    
    public static void main(String[] args) throws InterruptedException {
        LinkedBlockingQueue<Integer> queue = new LinkedBlockingQueue<>();
        Runnable r1 = () -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            queue.offer(1);
        };
        Runnable r2 = () -> {
            try {
                System.out.println(queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);
        t1.start();
        t2.start();
    }
}
