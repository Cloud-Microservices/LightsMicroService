package lightsmicroservice.logic.messages;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lightsmicroservice.boundaries.externalBoundary.DeviceBoundary;
import lightsmicroservice.boundaries.externalBoundary.MessageBoundary;
import lightsmicroservice.logic.LightsService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class MessageListener {

    @Value("${target.topic.name:topic1}")
    private String targetTopic;
    private StreamBridge kafka;
    private ObjectMapper objectMapper;
    private LightsService lightsService;
    private Log logger = LogFactory.getLog(MessageListener.class);
    private String serviceName;

    @Value("${spring.application.name:LightsMicroService}")
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public MessageListener(StreamBridge kafka, LightsService lightsService) {
        this.kafka = kafka;
        this.lightsService = lightsService;
    }

    @PostConstruct
    public void init() {
        objectMapper = new ObjectMapper();
    }

    @Bean
    public Consumer<String> messageSink() {
        return message -> {
            try {
                logger.debug("Got message from kafka: " + message);
                MessageBoundary receivedMessage = objectMapper.readValue(message, MessageBoundary.class);

                if(!isValidMessage(receivedMessage)) {
                    logger.error("Error processing message: cannot read device details");
                    return;
                }
                if (!receivedMessage.getMessageType().equals("deviceNotification")) {
                    logger.debug("Message is not a device notification, ignoring it");
                    return;
                }
                if (isMyOwnMessage(receivedMessage)) {
                    logger.debug("Message is from this service, ignoring it");
                    return;
                }

                logger.info("Handling message from kafka...");
                processMessage(receivedMessage);

            }
            catch (Exception e) {
                logger.error("Error processing message: " + e.getMessage());
            }
        };
    }

    private boolean isValidMessage(MessageBoundary message) {
        return message.getMessageDetails() != null
                && message.getMessageDetails().containsKey("device")
                && message.getMessageDetails().get("device") != null;
    }

    private boolean isMyOwnMessage(MessageBoundary message) {
        return message.getExternalReferences().get(0).getService().equals(serviceName);
    }

    private void processMessage(MessageBoundary message) {
        DeviceBoundary device = objectMapper.convertValue(message.getMessageDetails().get("device"), DeviceBoundary.class);
        if (device.getType().equals("Light")) {
            String msg = message.getSummary().toLowerCase();
            if (msg.contains("create"))
                logger.info("Got create light device from kafka");
            else if (msg.contains("delete") || msg.contains("remove"))
                logger.info("Got remove light device from kafka");
            else if (msg.contains("update")) {
                if(msg.contains("status"))
                    logger.info("Got update light status from kafka");
                else
                    logger.info("Got update light device from kafka");
            }
        } else {
            logger.error("Device is not a light device.");
        }
    }
}