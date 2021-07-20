package integration;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.RPCHook;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;

/**
 * @author parry
 * @date 2021/07/20
 */
public class Producer {
    
    private DefaultMQProducer producer;
    
    public Producer(String group, String nameSrvAddr, Integer timeout, RPCHook rpcHook) throws Exception {
        producer = new DefaultMQProducer(group, rpcHook);
        producer.setNamesrvAddr(nameSrvAddr);
        producer.setSendMsgTimeout(timeout);
        producer.start();
    }
    
    public Producer(String group, String nameSrvAddr, Integer timeout) throws Exception {
        this(group, nameSrvAddr, timeout, null);
    }
    
    public Producer(GlobalConfig config) throws Exception {
        this(config.getProducerGroup(), config.getNameSrvAddr(), config.getTimeout());
    }
    
    public SendResult send(String topic, String tag, String properties, String body) throws UnsupportedEncodingException, InterruptedException, RemotingException, MQClientException, MQBrokerException {
        Message message = new Message(topic, tag, properties, body.getBytes(RemotingHelper.DEFAULT_CHARSET));
        return producer.send(message);
    }
    
    public SendResult send(String topic, String tag, String body) throws UnsupportedEncodingException, InterruptedException, RemotingException, MQClientException, MQBrokerException {
        return send(topic, tag, null, body);
    }
    
    public void asyncSend(String topic, String tag, String properties, String body, SendCallback callback) throws UnsupportedEncodingException, InterruptedException, RemotingException, MQClientException, MQBrokerException {
        Message message = new Message(topic, tag, properties, body.getBytes(RemotingHelper.DEFAULT_CHARSET));
        if (callback == null) {
            callback = new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                
                }
            
                @Override
                public void onException(Throwable e) {
                
                }
            };
        }
        producer.send(message, callback);
    }
    
    public void asyncSend(String topic, String tag, String body, SendCallback callback) throws UnsupportedEncodingException, RemotingException, MQClientException, MQBrokerException, InterruptedException {
        asyncSend(topic, tag, null, body, callback);
    }
}
