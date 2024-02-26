package lightsMicroService.logic;

import lightsMicroService.dal.MessageCrud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import lightsMicroService.boundaries.MessageBoundary;

import java.util.Date;

@Service
public class MessageServiceImpl implements MessageService {

    private MessageCrud messageCrud;

    @Autowired
    public void setMessageCrud(MessageCrud messageCrud) {
        this.messageCrud = messageCrud;
    }

    @Override
    public Mono<MessageBoundary> publishMessage(MessageBoundary message) {
        message.setMessageId(null);
        message.setPublishedTimestamp(new Date());
        return Mono.just(message)
                .map(MessageBoundary::toEntity)
                .flatMap(this.messageCrud::save)
                .map(MessageBoundary::new);
    }

    @Override
    public Flux<MessageBoundary> getAllMessages() {
        return this.messageCrud
                .findAll()
                .map(MessageBoundary::new);
    }

    @Override
    public Mono<MessageBoundary> getMessageById(String messageId) {
        return this.messageCrud
                .findById(messageId)
                .map(MessageBoundary::new);
    }

    @Override
    public Mono<Void> deleteAllMessages() {
        return this.messageCrud
                .deleteAll();
    }

    @Override
    public Flux<MessageBoundary> getMessagesByExternalReference(String service, String externalServiceId) {
        return this.messageCrud
                .findAll()
                .filter(message -> message.getExtrenalReferences().stream()
                        .anyMatch(ref -> ref.getService().equals(service) && ref.getExternalServiceId().equals(externalServiceId)))
                .map(MessageBoundary::new);
    }


}
