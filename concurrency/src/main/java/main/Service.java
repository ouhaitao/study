package main;

public class Service implements Runnable {

    private int a = 0;

    @Override
    public void run() {
        try {
            a++;
            Thread.sleep(500);
            System.out.println(a);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
