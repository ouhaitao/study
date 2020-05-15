package org.ouht.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import ouht.service.Service;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisApplicationTests {

    @Autowired
    private Service service;

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void contextLoads() {
        System.out.println(service.getHash("o"));
//        service.age("oht",21);
//        service.remove("o");
        System.out.println(redisTemplate.getValueSerializer());
        redisTemplate.opsForValue().set("name","111");
    }

}
