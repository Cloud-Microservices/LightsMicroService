package rsocketmessagingservice.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import rsocketmessagingservice.boundaries.MessageBoundary;
import rsocketmessagingservice.dal.MessageCrud;

import java.util.Date;

@Service
public class MessageServiceImpl implements MessageService {

    private MessageCrud messageCrud;

    @Autowired
    public void setMessageCrud(MessageCrud messageCrud) {
        this.messageCrud = messageCrud;
    }

    @Override
    public Mono<MessageBoundary> publish_message(MessageBoundary message) {
        message.setMessageId(null);
        message.setPublishedTimestamp(new Date());
        return Mono.just(message)
                .map(MessageBoundary::toEntity)
                .flatMap(this.messageCrud::save)
                .map(MessageBoundary::new);
    }

}
