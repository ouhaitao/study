package ouht.Jedis;


import org.springframework.util.CollectionUtils;
import redis.clients.jedis.*;

import java.util.Arrays;
import java.util.List;

public class Main {
    
    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1");
        jedis.flushAll();
        System.out.println(jedis.ping());
        System.out.println(jedis.set("ouht", "ouht"));
        
//        ScanParams scanParams = new ScanParams();
//        scanParams = scanParams.match("*");
//        scanParams = scanParams.count(2);
//        String cursor = "0";
//        do {
//            ScanResult<String> result = jedis.scan(cursor, scanParams);
//            cursor = result.getStringCursor();
//            result.getResult().forEach(System.out::println);
//        } while (!cursor.endsWith("0"));
    
//        事务
//        Transaction multi = jedis.multi();
//        multi.set("key", "value");
//        multi.sadd("key", "1");
//        multi.set("key", "value1");
//        List<Object> exec = multi.exec();
//        exec.forEach(System.out::println);
//        List<Response<?>> responses = multi.execGetResponse();
//        responses.forEach(System.out::println);

//        管道
//        Pipeline pipelined = jedis.pipelined();
//        pipelined.set("key1", "value1");
//        pipelined.set("key2", "value2");
//        pipelined.sync();
    }
}
