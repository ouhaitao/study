package spi.adaptive;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.ExtensionLoader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author parry
 * @date 2020/12/30
 */
public class Test {
    
    public static void main(String[] args) {
        URL url = new URL("portocol", "1.1.1.1", 1);
//        extension
        IService service = ExtensionLoader.getExtensionLoader(IService.class).getExtension("impl2");
        service.say(url);
        System.out.println();
//        adaptiveExtension
        Map<String, String> map = new HashMap<>();
        map.put("key1", "impl1");
        map.put("key2", "impl2");
        url = new URL("portocol", "1.1.1.1", 1, map);
        service = ExtensionLoader.getExtensionLoader(IService.class).getAdaptiveExtension();
        service.say(url);
        System.out.println();
//        activeExtension
        List<IService> services = ExtensionLoader.getExtensionLoader(IService.class).getActivateExtension(url, "", "group1");
        for (IService iService : services) {
            iService.say(url);
        }
    }
    
    
    /**
     * getAdaptiveExtension返回的service最终会生成如下的代码
     * package spi.adaptive;
     * import com.alibaba.dubbo.common.extension.ExtensionLoader;
     * public class IService$Adaptive implements spi.adaptive.IService {
     * public void say(com.alibaba.dubbo.common.URL arg0) {
     * if (arg0 == null) throw new IllegalArgumentException("url == null");
     * com.alibaba.dubbo.common.URL url = arg0;
     * String extName = url.getParameter("key1");
     * if(extName == null) throw new IllegalStateException("Fail to get extension(spi.adaptive.IService) name from url(" + url.toString() + ") use keys([key1])");
     * <p>
     * spi.adaptive.IService extension = (spi.adaptive.IService)ExtensionLoader.getExtensionLoader(spi.adaptive.IService.class).getExtension(extName);extension.say(arg0);
     * }
     * }
     */
}
