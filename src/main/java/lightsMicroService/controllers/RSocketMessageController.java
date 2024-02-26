package lightsMicroService.controllers;

import lightsMicroService.boundaries.IdBoundary;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import lightsMicroService.boundaries.ExternalReferenceBoundary;
import lightsMicroService.boundaries.MessageBoundary;
import lightsMicroService.logic.MessageService;

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

    @MessageMapping("getAll-req-stream")
    public Flux<MessageBoundary> getAllMessages() {
        this.logger.debug("invoking: getAll-req-stream");
        return this.messages.getAllMessages();
    }


    @MessageMapping("getMessagesByIds-channel")
    public Flux<MessageBoundary> getMessagesByIds(Flux<IdBoundary> ids) {
        this.logger.debug("invoking: getMessagesByIds-channel");
        return ids
                .flatMap(id -> this.messages.getMessageById(id.getMessageId()));
    }

    @MessageMapping("deleteAll-fire-and-forget")
    public Mono<Void> deleteAllMessages() {
        this.logger.debug("invoking: deleteAll-fire-and-forget");
        return messages.deleteAllMessages();
    }


    @MessageMapping("getMessagesByExternalReferences-channel")
    public Flux<MessageBoundary> getMessagesByExternalReferences(Flux<ExternalReferenceBoundary> externalRefs) {
        this.logger.debug("invoking: getMessagesByExternalReferences-channel");
        return externalRefs
                .flatMap(ref -> this.messages.getMessagesByExternalReference(ref.getService(), ref.getExternalServiceId()));
    }
}
