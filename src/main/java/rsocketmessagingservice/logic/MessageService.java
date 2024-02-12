package rsocketmessagingservice.logic;

import reactor.core.publisher.Mono;
import rsocketmessagingservice.boundaries.MessageBoundary;

public interface MessageService {
    public Mono<MessageBoundary> publish_message(MessageBoundary message);
}
