package ouht.Jedis;


import org.springframework.util.CollectionUtils;
import redis.clients.jedis.*;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

public class Main {
    
    private static JedisCluster jedisCluster1 = new JedisCluster(new HostAndPort("127.0.0.1", 6379));
    private static JedisCluster jedisCluster2 = new JedisCluster(new HostAndPort("127.0.0.1", 6380));
    private static JedisCluster jedisCluster3 = new JedisCluster(new HostAndPort("127.0.0.1", 6381));
    private static Jedis jedis0 = new Jedis("127.0.0.1", 6378);
    private static Jedis jedis1 = new Jedis("127.0.0.1", 6379);
    private static Jedis jedis2 = new Jedis("127.0.0.1", 6380);
    private static Jedis jedis3 = new Jedis("127.0.0.1", 6381);
    
    public static void main(String[] args) {
    
    }
    
    public static void multi() {
//        客户端只能同时开启一个事务
        Transaction multi = jedis0.multi();
        multi.set("date", "1");
        multi = jedis0.multi();
        System.out.println(multi.get("date"));
        multi.set("date", "2");
        multi.exec();
    }
}
