package lightsMicroService.dal;

import lightsMicroService.boundaries.LightBoundary;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
//TODO: CHANGE AND CREATE A LIGHT ENTITY
public interface LightsCrud extends ReactiveMongoRepository<LightBoundary, String> {

}
