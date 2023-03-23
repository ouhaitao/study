package spi.adaptive;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.Activate;

/**
 * @author parry
 * @date 2020/12/30
 */
@Activate(group = "group1", before = "impl2")
public class ServiceImpl1 implements IService {
    
    @Override
    public void say(URL url) {
        System.out.println("impl1");
    }
}
