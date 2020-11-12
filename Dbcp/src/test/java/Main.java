import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Before;
import org.junit.Test;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 * @author parry
 * @date 2020/08/20
 */
public class Main {
    
    private String driverClass = "com.mysql.cj.jdbc.Driver";
    
    private String url = "jdbc:mysql://127.0.0.1:3306/test?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=UTC&useAffectedRows=true";
    
    private String username = "root";
    
    private String password = "iamalone11";
    
    private int maxIdle = 50;
    
    private int maxActive = 50;
    
    private int maxWait = -1;
    
    private DataSource dataSource;
    
    @Before
    public void init() {
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName(driverClass);
        ds.setUrl(url);
        ds.setUsername(username);
        ds.setPassword(password);
        ds.setMaxActive(maxActive);
        ds.setMaxIdle(maxIdle);
        ds.setMaxWait(maxWait);
        dataSource = ds;
        
    }
    
    private void task(CyclicBarrier barrier, CountDownLatch latch) {
        try {
            barrier.await();
            Connection connection = dataSource.getConnection();
            String sql = "select sleep(1)";
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet resultSet = st.executeQuery();
            connection.close();
            latch.countDown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void startThread(int count, CyclicBarrier barrier, CountDownLatch latch) {
        Runnable task = () -> {
            task(barrier, latch);
        };
        for (int i = 0; i < count; i++) {
            Thread thread = new Thread(task);
            thread.start();
        }
    }
    
//    @Test
    public void test() throws Exception {
        Connection connection = dataSource.getConnection();
        String sql = "select * from a";
        PreparedStatement st = connection.prepareStatement(sql);
        ResultSet resultSet = st.executeQuery();
        while (resultSet.next()) {
            System.out.println("id:" + resultSet.getString(1) + "\tb_id:" + resultSet.getString(2));
        }
        connection.close();
    }
    
    @Test
    public void one() throws Exception {
        int threadNum = 1;
        CountDownLatch countDownLatch = new CountDownLatch(threadNum);
        startThread(threadNum, new CyclicBarrier(threadNum), countDownLatch);
        countDownLatch.await();
    }
    
    @Test
    public void ten() throws Exception {
        int threadNum = 10;
        CountDownLatch countDownLatch = new CountDownLatch(threadNum);
        startThread(threadNum, new CyclicBarrier(threadNum), countDownLatch);
        countDownLatch.await();
    }
    
    @Test
    public void hundred() throws Exception {
        int threadNum = 100;
        CountDownLatch countDownLatch = new CountDownLatch(threadNum);
        startThread(threadNum, new CyclicBarrier(threadNum), countDownLatch);
        countDownLatch.await();
    }
    
    @Test
    public void thousand() throws Exception {
        int threadNum = 1000;
        CountDownLatch countDownLatch = new CountDownLatch(threadNum);
        startThread(threadNum, new CyclicBarrier(threadNum), countDownLatch);
        countDownLatch.await();
    }
    
    @Test
    public void tenThousand() throws Exception {
        int threadNum = 10000;
        CountDownLatch countDownLatch = new CountDownLatch(threadNum);
        startThread(threadNum, new CyclicBarrier(threadNum), countDownLatch);
        countDownLatch.await();
    }
    
}
