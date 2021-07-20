package integration;

import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @author parry
 * @date 2021/07/20
 */
@SpringBootApplication
public class RocketMqBootStrap {
    
    public static void main(String[] args) throws InterruptedException, RemotingException, MQClientException, MQBrokerException, UnsupportedEncodingException {
        ConfigurableApplicationContext context = SpringApplication.run(RocketMqBootStrap.class);
        Producer producer = context.getBean("producer", Producer.class);
        System.out.println(producer.send("topicTest", "tagTest", "hello world"));
    }
    
    @Bean
    public Producer producer() throws Exception {
        return new Producer("springProducer", "127.0.0.1:9876", 1000);
    }
    
    @Bean
    public PushConsumer consumer() throws Exception {
        return new PushConsumer("springConsumer", "127.0.0.1:9876", "topicTest", "tagTest", new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                System.out.println(msgs);
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
    }
    
}
