public class interrupt {

    private static Object lock = new Object();
    
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new service());
        thread.start();
        Thread.sleep(500);
        thread.interrupt();
        Thread.sleep(500);
        thread.interrupt();
        Thread.sleep(500);
        thread.interrupt();
        synchronized (lock) {
            Thread.sleep(500);
            thread.interrupt();
            Thread.sleep(10000);
        }
    }


    
    static class service implements Runnable {
    
        @Override
        public void run() {
            synchronized (this) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    System.out.println("wait interrupted flag=" + Thread.currentThread().isInterrupted());
                }
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("sleep interrupted flag=" + Thread.currentThread().isInterrupted());
            }
            while (!Thread.currentThread().isInterrupted()) {
            
            }
            System.out.println("end flag=" + Thread.currentThread().isInterrupted());
    
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lock) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    System.out.println("sync interrupted flag=" + Thread.currentThread().isInterrupted());
                }
            }
        }
    }
    
}
