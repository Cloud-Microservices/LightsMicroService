package rsocketmessagingservice.dal;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import rsocketmessagingservice.data.MessageEntity;

public interface MessageCrud extends ReactiveMongoRepository<MessageEntity, String> {
}
