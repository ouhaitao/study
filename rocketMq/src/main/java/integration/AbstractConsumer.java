package integration;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.MessageListener;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.client.exception.MQClientException;

import javax.annotation.PostConstruct;

/**
 * @author parry
 * @date 2021/07/20
 */
public abstract class AbstractConsumer {
    
    String group;
    
    String nameRrvAddr;
    
    String topic;
    
    String tag;
    
    public AbstractConsumer(String group, String nameSrvAddr, String topic, String tag) throws Exception {
        this.group = group;
        this.nameRrvAddr = nameSrvAddr;
        this.topic = topic;
        this.tag = tag;
    }
    
    @PostConstruct
    public abstract void afterProperties();
}
