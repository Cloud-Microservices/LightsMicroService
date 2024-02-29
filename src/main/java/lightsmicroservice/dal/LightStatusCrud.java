package lightsmicroservice.dal;

import lightsmicroservice.data.LightStatusEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface LightStatusCrud  extends ReactiveMongoRepository<LightStatusEntity, String> {
}
