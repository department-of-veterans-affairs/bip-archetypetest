package gov.va.bip.archetypetest.api.provider;

import gov.va.bip.archetypetest.api.KafkaApi;
import gov.va.bip.archetypetest.kafka.consumer.KafkaConsumer;
import gov.va.bip.framework.log.BipLogger;
import gov.va.bip.framework.log.BipLoggerFactory;
import gov.va.bip.framework.swagger.SwaggerResponseMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ArchetypeKafkaTestResource implements KafkaApi, SwaggerResponseMessages {

    /** Logger instance */
    private static final BipLogger LOGGER = BipLoggerFactory.getLogger(ArchetypeTestResource.class);
    
    @Autowired
    KafkaConsumer kafkaConsumer;

    /**
     * Gets the most recent name sent through Kafka
     * @return the sample info response
     */
    @Override
    public ResponseEntity<String> getPersonFromKafka() {
        LOGGER.debug("getPersonFromKafka() method invoked");
        return new ResponseEntity<>(kafkaConsumer.getVetName(), HttpStatus.OK);
    }

}
