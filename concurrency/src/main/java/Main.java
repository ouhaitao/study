/**
 * @author parry
 * @date 2019/09/18
 */
public class Main {
    
    private int num = 0;
    
    public static void main(String[] args) throws InterruptedException {
        Main main = new Main();
        main.test();
    }
    
    private void test() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (; num < 100; ) {
                if (((++num) & 1) == 0) {
                    System.out.println(num);
                }
            }
        }
        );
        Thread t2 = new Thread(() -> {
            for (; num < 100; ) {
                if (((++num) & 1) != 0) {
                    System.out.println(num);
                }
            }
        }
        );
        t2.start();
        t1.start();
    }
}
