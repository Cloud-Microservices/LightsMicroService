package rsocketmessagingservice.controllers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;
import rsocketmessagingservice.boundaries.MessageBoundary;
import rsocketmessagingservice.logic.MessageService;

@Controller
public class RSocketMessageController {
    private MessageService messages;
    private Log logger = LogFactory.getLog(RSocketMessageController.class);

    @Autowired
    public void setMessages(MessageService messages) {
        this.messages = messages;
    }
    @MessageMapping("publish-message-req-resp")
    public Mono<MessageBoundary> create(@Payload MessageBoundary message) {
        this.logger.debug("invoking: publish-message-req-resp");
        return this.messages.publish_message(message);

    }

}
