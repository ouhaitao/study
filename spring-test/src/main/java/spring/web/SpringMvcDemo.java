package spring.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author parry
 * @date 2020/11/09
 */
@SpringBootApplication
public class SpringMvcDemo {
    
    public static void main(String[] args) {
        SpringApplication.run(SpringMvcDemo.class, args);
    }
    
    
    @RestController
    static class ControllerDemo {
        @RequestMapping("/test")
        public String test() {
            return "test";
        }
    }
}
