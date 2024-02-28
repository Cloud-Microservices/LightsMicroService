package lightsMicroService.logic;

import lightsMicroService.boundaries.LightBoundary;
import lightsMicroService.boundaries.LightStatusBoundary;
import lightsMicroService.boundaries.LocationStatusBoundary;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface LightsService {
    public Mono<LightBoundary> createLight(LightBoundary light);

    public Mono<Void> deleteLight(String id);

    public Mono<Void> deleteAll();

    public Flux<LightBoundary> updateLights(Flux<LightBoundary> lights);

    public Flux<LightStatusBoundary> updateLightsStatus(Flux<LightStatusBoundary> lightStatus);

    public Flux<LightStatusBoundary> updateLightsStatusByLocation(Mono<LocationStatusBoundary> updateLocationStatus);

    public Flux<LightStatusBoundary> updateAllLightsStatus(Mono<LightStatusBoundary> lightStatusBoundary);

    public Mono<LightBoundary> getLightById(String id);

    public Flux<LightBoundary> getAllLights();

    public Flux<LightStatusBoundary> getAllLightsStatus();

    public Flux<LightStatusBoundary> getAllLightsStatusByLocation(String location);

    public Mono<LightStatusBoundary> getSpecificLightsStatus(String id);
}
