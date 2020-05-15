package ouht.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

@org.springframework.stereotype.Service
//缓存的值序列化有问题，无法更改
public class Service {

    /**
        key指定缓存中对应的key，#p0是springEl表达式，代表方法的第一个参数，也可以#p0.属性名，例如#p0.class，表示第一个参数的class属性
        这里也可以写成#name
        value指定缓存于哪个cache上
        最后在缓存中的key是value::key，例如这里name为oht，name缓存中的key为name::oht
        condition属性指定缓存的条件,这里当name不等于oht时，就会缓存
        该方法每次执行前会先检查缓存中是否存在对于的值，有就拿出来直接返回，没有则执行方法
        一般用于对数据库的查询操作
     */
    @Cacheable(key = "#p0",value = "name",condition = "!#p0.equals('oht')")
    public String getHash(String name){
        System.out.println("getHash method is invoked");
        return String.valueOf(name.hashCode());
    }

    /**
     * 参数同上
     * 每次执行前不会检查缓存，而是执行后会更新对于key的缓存
     */
    @CachePut(key = "#p0+'::'+#p1",value = "name")
    public int age(String name,int age){
        System.out.println("age method is invoked");
        return age;
    }

    /**
     * 参数同上，allEntries表示是否需要清除所有key，beforeInvocation表示是否在方法调用之后执行删除key的操作
     */
    @CacheEvict(key = "#p0",value = "name",allEntries = false,beforeInvocation = false)
    public String remove(String name){
        return name;
    }

    //getHash()方法的缓存无效
    public String getHash1(String name){
        return getHash(name);
    }

}
