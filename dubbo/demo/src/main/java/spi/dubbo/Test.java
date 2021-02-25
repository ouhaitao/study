package spi.dubbo;

import com.alibaba.dubbo.common.extension.ExtensionLoader;

/**
 * @author parry
 * @date 2020/12/30
 */
public class Test {
    
    public static void main(String[] args) {
        IService service = ExtensionLoader.getExtensionLoader(IService.class).getDefaultExtension();
        service.sayHello();
    }
    
}
