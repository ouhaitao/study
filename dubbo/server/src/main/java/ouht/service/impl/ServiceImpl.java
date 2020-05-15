package ouht.service.impl;


import service.Service;

@com.alibaba.dubbo.config.annotation.Service
public class ServiceImpl implements Service {

    @Override
    public String sayHello(String name) {
        return "hello "+name;
    }

}
