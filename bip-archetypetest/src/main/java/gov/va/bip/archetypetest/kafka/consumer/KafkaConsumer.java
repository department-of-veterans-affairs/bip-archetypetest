package gov.va.bip.archetypetest.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(value = "spring.kafka.properties.consumer.enabled", havingValue = "true")
public class KafkaConsumer {

    final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);
    
    private String vetName;

    @KafkaListener(topics = "#{'${io.confluent.developer.config.topic.name}'}")
    public void basicConsume(final ConsumerRecord<String, String> consumerRecord) {
        logger.info("received {}", consumerRecord);
        vetName = consumerRecord.value();
    }

    public String getVetName(){
        return vetName;
    }
}
