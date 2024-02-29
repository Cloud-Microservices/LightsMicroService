package lightsmicroservice.logic;

import lightsmicroservice.boundaries.LightBoundary;
import lightsmicroservice.boundaries.LightStatusBoundary;
import lightsmicroservice.boundaries.LocationStatusBoundary;
import lightsmicroservice.boundaries.StatusBoundary;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface LightsService {
    public Mono<LightBoundary> createLight(LightBoundary light);

    public Mono<Void> deleteLight(String id);

    public Mono<Void> deleteAll();

    public Mono<LightBoundary> updateLight(LightBoundary light);

    public Mono<LightStatusBoundary> updateSpecificLightStatus(LightStatusBoundary lightStatus);

    public Flux<LightStatusBoundary> updateLightsStatusByLocation(Mono<LocationStatusBoundary> updateLocationStatus);

    public Flux<LightStatusBoundary> updateAllLightsStatus(Mono<StatusBoundary> statusBoundary);

    public Mono<LightBoundary> getLightById(String id);

    public Flux<LightBoundary> getAllLights();

    public Flux<LightStatusBoundary> getAllLightsStatus();

    public Flux<LightStatusBoundary> getAllLightsStatusByLocation(String location);

    public Mono<LightStatusBoundary> getSpecificLightsStatus(String id);
}
