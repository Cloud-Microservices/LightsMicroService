package rsocketmessagingservice.logic;

import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import rsocketmessagingservice.boundaries.MessageBoundary;

public interface MessageService {
    public Mono<MessageBoundary> publishMessage(MessageBoundary message);

    public Flux<MessageBoundary> getAllMessages();

    public Mono<MessageBoundary> getMessageById(String messageId);

    public Mono<Void> deleteAllMessages();

    public  Flux<MessageBoundary>getMessagesByExternalReference(String service, String externalServiceId);
}
