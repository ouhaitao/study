package integration;

/**
 * @author parry
 * @date 2021/07/20
 */
public class GlobalConfig {
    
    private String nameSrvAddr;
    
    /**
     * 生产者属性
     */
    private String producerGroup;
    
    private Integer timeout;
    
    /**
     * 消费者属性
     */
    private String consumerGroup;
    
    private String topic;
    
    private String tag;
    
    public GlobalConfig(String nameSrvAddr, String projectName, Integer timeout, String topic, String tag) {
        this.nameSrvAddr = nameSrvAddr;
        this.producerGroup = projectName + "_producer";
        this.timeout = timeout;
        this.consumerGroup = projectName + "_consumer";
        this.topic = topic;
        this.tag = tag;
    }
    
    public String getNameSrvAddr() {
        return nameSrvAddr;
    }
    
    public String getConsumerGroup() {
        return consumerGroup;
    }
    
    public String getProducerGroup() {
        return producerGroup;
    }
    
    public String getTopic() {
        return topic;
    }
    
    public String getTag() {
        return tag;
    }
    
    public Integer getTimeout() {
        return timeout;
    }
}
