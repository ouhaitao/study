package ouht;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import com.alibaba.dubbo.config.spring.context.annotation.EnableDubboConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableDubbo
public class ServerApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(ServerApplication.class, args);
    }
    
}
