package ouht.service.impl;


import service.Service;

@com.alibaba.dubbo.config.annotation.Service(timeout = Integer.MAX_VALUE)
public class ServiceImpl implements Service {

    @Override
    public String sayHello(String name) {
        return "hello "+name;
    }

}
