package lightsmicroservice.dal;

import lightsmicroservice.data.LightEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
public interface LightsCrud extends ReactiveMongoRepository<LightEntity, String> {

}
