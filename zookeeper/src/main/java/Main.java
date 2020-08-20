import org.apache.zookeeper.ZooKeeper;

/**
 * @author parry
 * @date 2020/08/04
 */
public class Main {
    
    public static void main(String[] args) throws Exception {
        ZooKeeper zooKeeper = new ZooKeeper("127.0.0.1:2181", 10000, (event) -> {
            System.out.println("connected");
        });
        while (zooKeeper.getState() != ZooKeeper.States.CONNECTED);
//        System.out.println(zooKeeper.create("/test", "123".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT));
        System.out.println(zooKeeper.getChildren("/", (event) -> {
            System.out.println("getChildren");
        }));
    }
    
   
}
