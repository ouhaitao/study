package jpa;

import jpa.entity.Admin;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.proxy.AbstractLazyInitializer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.io.Serializable;
import java.lang.reflect.Method;

/**
 * @author parry
 * @date 2019/11/08
 */
public class Main {
    
    /**
     * 1. persist(Object o) 将新建状态的实体与持久化上下文关联，变为托管态。
     * 2. remove(Object o) 将托管态的实体变为删除态，对应数据库的删除操作。
     * 3. merge(Object o) 将游离态的实体重新关联持久化上下文，变为托管态。
     * 4. find(Class<T> entityClass, Object key) 根据主键获取实体，取出来的实体为托管态。
     * 5. getReference(Class<T> entityClass, Object key) 根据主键获取实体，跟find()方法的区别在于find()是立即查询缓存/数据库，
     * 而本方法是懒加载，即当使用到获得的实体时才执行操作。
     * 6. refresh(Object o) 重新从数据库实体的数据。
     * 7. flush() 将持久化上下文中的实体状态同步到数据库，即真正的操作数据库，等同于hibernate中session的提交。
     * 8. setFlushMode(FlushModeType flushMode) 设置flush模式，
     * 1. 默认为执行查询之前或者事务提交时才进行（默认）
     * 2. 事务提交时才进行刷新操作
     * ps：这仅仅是JPA定义的两种flush模式，并不表示JPA实现厂商只有这两种模式。
     */
    
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myJpa");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
//        find(entityManager, null);
//        getReference(entityManager);
//        persist(entityManager);
//        remove(entityManager);
//        merge(entityManager);
//        flush(entityManager);
//        refresh(entityManager);
        transaction.commit();
    }
    
    
    private static Admin find(EntityManager entityManager, Integer id) {
        if (id == null) {
            id = 2;
        }
        Admin admin = entityManager.find(Admin.class, id);
        System.out.println(admin);
        return admin;
    }
    
    /**
     * hibernate实现getReference的方式是采用修改字节码生成代理类的方式，
     * getReference返回的是代理类，对所有方法进行拦截，在拦截方法中判断
     * 如果该对象为初始化，则查询数据库对其进行初始化。
     * 创建并绑定拦截器的方法是{@link org.hibernate.proxy.pojo.bytebuddy.ByteBuddyProxyFactory#getProxy(Serializable, SharedSessionContractImplementor)}
     * 在代理类第一次执行对象方法时，拦截器的intercept()方法的第一个if判断，即代码第二行会返回true，调用其父类的getImplementation()方法，
     * 在该方法中查询数据库并完成初始化
     * 拦截器方法{@link org.hibernate.proxy.pojo.bytebuddy.ByteBuddyInterceptor#intercept(Object, Method, Object[])}
     * 父类getImplementation()方法{@link AbstractLazyInitializer#getImplementation()}
     * 父类初始化方法{@link AbstractLazyInitializer#initialize()}
     */
    private static Admin getReference(EntityManager entityManager) {
        Admin admin = entityManager.getReference(Admin.class, 2);
        System.out.println(admin);
        return admin;
    }
    
    private static Admin persist(EntityManager entityManager) {
        Admin admin = new Admin();
        admin.setAge(1);
        admin.setPermissions("3");
        entityManager.persist(admin);
        System.out.println(admin);
        return admin;
    }
    
    private static void remove(EntityManager entityManager) {
        entityManager.remove(entityManager.getReference(Admin.class, 4));
    }
    
    private static void merge(EntityManager entityManager) {
        Admin admin = find(entityManager, 3);
        entityManager.detach(admin);
        admin.setAge(100);
    }
    
    private static void flush(EntityManager entityManager) {
        Admin admin = find(entityManager, 3);
        admin.setAge(100);
        entityManager.flush();
    }
    
    private static void refresh(EntityManager entityManager) {
        Admin admin = find(entityManager, 2);
        System.out.println(admin.getAge());
        admin.setAge(admin.getAge() + 10);
        entityManager.refresh(admin);
        System.out.println(admin);
    }
}
