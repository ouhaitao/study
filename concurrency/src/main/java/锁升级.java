import org.openjdk.jol.info.ClassLayout;

/**
 * @author parry
 * @date 2019/09/18
 * 偏向锁启动有数秒的延迟 所以需要配置JVM参数-XX:+UseBiasedLocking -XX:BiasedLockingStartupDelay=0
 *
 */
public class 锁升级 {
    
    private static Object lock = new Object();
    
    /**
     *  OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
     *       0     4        (object header)                           05 00 00 00 (00000101 00000000 00000000 00000000) (5)
     *                                                                             00000101 这个101的第一个1表示是否为偏向锁 01表示无锁（偏向锁的这个值一直为01） 下面都是在讨论这三位
     */
    
    public static void main(String[] args) throws Exception {
//        锁初始状态 1 01 偏向锁 无锁
        System.out.println(ClassLayout.parseInstance(lock).toPrintable());
//        单线程 1 01 偏向锁 无锁
        new Thread(锁升级::sync).start();
        Thread.sleep(1000);
//        多线程 0 00 不是偏向锁 轻量级锁
        sync();
//        多线程 0 00 不是偏向锁 轻量级锁
        new Thread(() ->{
            sync();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
//        多线程且并发 0 10 不是偏向锁 重量级锁
        sync();
//        0 01 不是偏向锁 无锁
//        恢复无锁状态 锁升级之后无法降级 所以之后加锁都是重量级锁
        System.out.println(ClassLayout.parseInstance(lock).toPrintable());
    }
    
    private static void sync(){
        synchronized (lock){
            System.out.println(ClassLayout.parseInstance(lock).toPrintable());
        }
    }
}
