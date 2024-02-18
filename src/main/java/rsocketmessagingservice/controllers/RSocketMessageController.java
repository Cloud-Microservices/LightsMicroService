package rsocketmessagingservice.controllers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import rsocketmessagingservice.boundaries.IdBoundary;
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
        return this.messages.publishMessage(message);

    }

    /*
     TODO: Not tested!
     */
    @MessageMapping("getAll-req-stream")
    public Flux<MessageBoundary> getAllMessages() {
        this.logger.debug("invoking: getAll-req-stream");
        return this.messages.getAllMessages();
    }


    /*
     TODO: Not tested!
           The service returns Mono<MessageBoundary> for every messageId,
           The client expects a Flux<MessageBoundary>
           Check if it's ok.
     */
    @MessageMapping("getMessagesByIds-channel")
    public Flux<MessageBoundary> getMessagesByIds(Flux<IdBoundary> ids) {
        this.logger.debug("invoking: getMessagesByIds-channel");
        return ids
                .flatMap(id -> this.messages.getMessageById(id.getMessageId()));
    }

    /*
     TODO: Not tested!
    */
    @MessageMapping("deleteAll-fire-and-forget")
    public Mono<Void> deleteAllMessages() {
        this.logger.debug("invoking: deleteAll-fire-and-forget");
        return messages.deleteAllMessages();
    }

}
