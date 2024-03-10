package lightsmicroservice.logic.messages;

import com.fasterxml.jackson.databind.ObjectMapper;
import lightsmicroservice.boundaries.externalBoundary.DeviceBoundary;
import lightsmicroservice.boundaries.externalBoundary.ExternalReferenceBoundary;
import lightsmicroservice.boundaries.externalBoundary.MessageBoundary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class MessagesHandler {

    private String serviceName;

    @Value("${spring.application.name:LightsMicroService}")
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public MessageBoundary createMessage(String summary, String messageType, DeviceBoundary deviceBoundary) {
        MessageBoundary message = new MessageBoundary();
        message.setMessageId(UUID.randomUUID().toString())
                .setPublishedTimestamp(new Date())
                .setExternalReferences(List.of(new ExternalReferenceBoundary()
                        .setService(serviceName)
                        .setExternalServiceId("42")))
                .setMessageType(messageType)
                .setSummary(summary)
                .setMessageDetails(new HashMap<>(Map.of("device", deviceBoundary)));

        return message;
    }

}
