package ouht.Jedis;


import redis.clients.jedis.Jedis;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;

public class Main {

    public static void main(String[] args) {
        Jedis jedis=new Jedis("127.0.0.1");
        System.out.println(jedis.ping());
        System.out.println(jedis.set("ouht","ouht"));
        ScanParams scanParams=new ScanParams();
        scanParams=scanParams.match("*");
        scanParams=scanParams.count(2);
        String cursor="0";
        do {
            ScanResult<String> result=jedis.scan(cursor,scanParams);
            cursor=result.getStringCursor();
            result.getResult().forEach(System.out::println);
        }while (!cursor.endsWith("0"));
    }
}
