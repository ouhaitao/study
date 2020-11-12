package spring;

import lombok.Setter;
import mybatis.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * @author parry
 * @date 2020/10/14
 */
@Transactional(rollbackFor = Exception.class)
@Setter
public class MyService {
    
    private TestMapper testMapper;
    
    public String test(String param) {
        return testMapper.test(param);
    }
}
