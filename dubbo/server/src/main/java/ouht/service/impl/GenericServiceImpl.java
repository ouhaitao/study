package ouht.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import org.ouht.service.GenericServiceDemo;


@Service
public class GenericServiceImpl implements GenericServiceDemo {

    @Override
    public String sayBye(String name) {
        return "bye," + name;
    }
}
