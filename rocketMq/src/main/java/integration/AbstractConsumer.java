package integration;

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
