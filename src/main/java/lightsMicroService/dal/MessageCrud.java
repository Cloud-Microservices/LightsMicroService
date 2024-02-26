package lightsMicroService.dal;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import lightsMicroService.data.MessageEntity;

public interface MessageCrud extends ReactiveMongoRepository<MessageEntity, String> {
}
