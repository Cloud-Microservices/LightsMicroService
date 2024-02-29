package lightsmicroservice.dal;

import lightsmicroservice.data.LightEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface LightsCrud extends ReactiveMongoRepository<LightEntity, String> {

    Flux<LightEntity> findAllByLocation(String location);


}
