package ouht;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import com.alibaba.dubbo.config.spring.context.annotation.EnableDubboConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ouht.service.ConsumerService;

@EnableDubbo
@SpringBootApplication
public class ConsumerApplication {

    
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(ConsumerApplication.class, args);
        System.out.println(((ConsumerService) context.getBean("consumerService")).sayHello());
        
    }
    

}
