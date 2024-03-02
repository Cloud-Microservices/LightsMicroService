package lightsmicroservice.logic;

import lightsmicroservice.boundaries.LightBoundary;
import lightsmicroservice.boundaries.LightStatusBoundary;
import lightsmicroservice.boundaries.LocationStatusBoundary;
import lightsmicroservice.boundaries.StatusBoundary;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface LightsService {
   Mono<LightBoundary> createLight(LightBoundary light);

   Mono<Void> deleteLight(String id);

   Mono<Void> deleteAll();

   Mono<LightBoundary> updateLight(LightBoundary light);

   Mono<LightStatusBoundary> updateSpecificLightStatus(LightStatusBoundary lightStatus);

   Flux<LightStatusBoundary> updateLightsStatusByLocation(LocationStatusBoundary updateLocationStatus);

   Flux<LightStatusBoundary> updateAllLightsStatus(StatusBoundary statusBoundary);

   Mono<LightBoundary> getLightById(String id);

   Flux<LightBoundary> getAllLights();

   Flux<LightStatusBoundary> getAllLightsStatus();

   Flux<LightStatusBoundary> getAllLightsStatusByLocation(String location);

   Mono<LightStatusBoundary> getSpecificLightsStatus(String id);

//   Flux<LightBoundary> getLightsByStatusIsOn(StatusBoundary status);
Flux<LightBoundary> getLightsByStatusIsOn(Boolean isOn);

}
