package spi.adaptive;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.Adaptive;
import com.alibaba.dubbo.common.extension.SPI;

@SPI
public interface IService {
    
    /**
     * 顺序匹配
     * 该注解的值必须是URL中的属性或者parameters中的值
     *
     * 如果参数非URL,则会直接调用对应key的get方法，在这里即为getKey()，在生成代理类的代码中可以看到相应流程
     */
    @Adaptive({"key1", "key2"})
    void say(URL url);
}
