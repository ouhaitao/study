package integration;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.client.exception.MQClientException;

/**
 * @author parry
 * @date 2021/07/20
 */
public class PushConsumer extends AbstractConsumer {
    
    private DefaultMQPushConsumer consumer;
    
    public PushConsumer(GlobalConfig config, MessageListenerConcurrently listener) throws Exception {
        super(config.getConsumerGroup(), config.getNameSrvAddr(), config.getTopic(), config.getTag());
        consumer = new DefaultMQPushConsumer(group);
        consumer.setNamesrvAddr(nameRrvAddr);
        consumer.subscribe(topic, tag);
        consumer.registerMessageListener(listener);
    }
    
    public PushConsumer(GlobalConfig config, MessageListenerOrderly listener) throws Exception {
        super(config.getConsumerGroup(), config.getNameSrvAddr(), config.getTopic(), config.getTag());
        consumer = new DefaultMQPushConsumer(group);
        consumer.setNamesrvAddr(nameRrvAddr);
        consumer.subscribe(topic, tag);
        consumer.registerMessageListener(listener);
    }
    
    public PushConsumer(String group, String nameSrvAddr, String topic, String tag, MessageListenerOrderly listener) throws Exception {
        super(group, nameSrvAddr, topic, tag);
        consumer = new DefaultMQPushConsumer(group);
        consumer.setNamesrvAddr(nameRrvAddr);
        consumer.subscribe(topic, tag);
        consumer.registerMessageListener(listener);
    }
    
    public PushConsumer(String group, String nameSrvAddr, String topic, String tag, MessageListenerConcurrently listener) throws Exception {
        super(group, nameSrvAddr, topic, tag);
        consumer = new DefaultMQPushConsumer(group);
        consumer.setNamesrvAddr(nameRrvAddr);
        consumer.subscribe(topic, tag);
        consumer.registerMessageListener(listener);
    }
    
    @Override
    public void afterProperties() {
        try {
            consumer.start();
        } catch (MQClientException e) {
            e.printStackTrace();
        }
    }
}
