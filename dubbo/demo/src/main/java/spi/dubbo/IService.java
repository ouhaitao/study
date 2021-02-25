package spi.dubbo;

import com.alibaba.dubbo.common.extension.SPI;

/**
 * @author parry
 * @date 2020/12/30
 */
@SPI("impl")
public interface IService {
    
    void sayHello();
    
}
