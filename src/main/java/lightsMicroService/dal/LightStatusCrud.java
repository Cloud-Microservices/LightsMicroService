package lightsMicroService.dal;

import lightsMicroService.data.LightEntity;
import lightsMicroService.data.LightStatusEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface LightStatusCrud  extends ReactiveMongoRepository<LightStatusEntity, String> {
}
