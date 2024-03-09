package lightsmicroservice.logic;

import com.fasterxml.jackson.databind.ObjectMapper;
import lightsmicroservice.boundaries.externalBoundary.MessageBoundary;
import org.springframework.stereotype.Component;

public class MessageHandler {

    private ObjectMapper jackson;

    public MessageHandler() {
        this.jackson = new ObjectMapper();
    }

    public MessageBoundary createMessage() {
        return new MessageBoundary();
    }


}
