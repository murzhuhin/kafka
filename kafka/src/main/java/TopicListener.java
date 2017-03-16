import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.listener.AbstractMessageListenerContainer;
import org.springframework.kafka.listener.KafkaDataListener;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.kafka.listener.config.ContainerProperties;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.Map;
import java.util.Properties;

public class TopicListener extends AbstractMessageListenerContainer {


    private static ConsumerFactory<Integer,String> consumerFactory;
    private static ContainerProperties containerProperties;

    protected TopicListener(ContainerProperties containerProperties) {
        super(containerProperties);
    }

    @Override
    protected void doStart() {
        System.out.print("hi");
    }

    @Override
    protected void doStop(Runnable runnable) {

    }

    public static void main(String[] args){

        Properties props = new Properties();
        props.put("bootstrap.servers","localhost:9092");
        props.put("value.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
        props.put("key.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
        props.put("group.id","mygroup");

        KafkaMessageListenerContainer listener = new KafkaMessageListenerContainer(
                new DefaultKafkaConsumerFactory(props), new ContainerProperties("topic","almu-test-1"));

        listener.setupMessageListener(
                new MessageListener() {
                    @Override
                    public void onMessage(Object o) {
                        System.out.println("hui");
                    }
                }
        );

        listener.start();
    }


    public void setContainerProperties(ContainerProperties containerProperties) {
        this.containerProperties = containerProperties;
    }

    public void setConsumerFactory(ConsumerFactory<Integer, String> consumerFactory) {
        this.consumerFactory = consumerFactory;
    }




}
