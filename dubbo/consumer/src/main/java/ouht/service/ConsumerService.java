package ouht.service;

import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ouht.factory.GenericFactory;
import service.Service;

@RestController
public class ConsumerService {

    @Autowired
    private GenericFactory genericFactory;
    
    @Reference(timeout = Integer.MAX_VALUE)
    private Service service;

    @GetMapping("sayBye")
    public String sayBye(String name){
        return genericFactory.$Invoke("org.ouht.service.GenericServiceDemo",
                "sayBye",
                new String[] {"java.lang.String"},
                new Object[] {name}).toString();
    }

    @GetMapping("sayHello")
    public String sayHello(String name){
        return genericFactory.$Invoke("org.ouht.service.Service",
                "sayHello",
                new String[] {"java.lang.String"},
                new Object[] {name}).toString();
    }
    
    public String sayHello() {
        return service.sayHello("world");
    }
}
