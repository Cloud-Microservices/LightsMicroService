package lightsMicroService.logic;

import lightsMicroService.boundaries.LightBoundary;
import lightsMicroService.boundaries.LightStatusBoundary;
import lightsMicroService.boundaries.LocationStatusBoundary;
import lightsMicroService.boundaries.StatusBoundary;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface LightsService {
    public Mono<LightBoundary> createLight(LightBoundary light);

    public Mono<Void> deleteLight(String id);

    public Mono<Void> deleteAll();

    public Mono<LightBoundary> updateLight(LightBoundary light);

    public Mono<LightStatusBoundary> updateSpecificLightStatus(Mono<LightStatusBoundary> lightStatus);

    public Flux<LightStatusBoundary> updateLightsStatusByLocation(Mono<LocationStatusBoundary> updateLocationStatus);

    public Flux<LightStatusBoundary> updateAllLightsStatus(Mono<StatusBoundary> statusBoundary);

    public Mono<LightBoundary> getLightById(String id);

    public Flux<LightBoundary> getAllLights();

    public Flux<LightStatusBoundary> getAllLightsStatus();

    public Flux<LightStatusBoundary> getAllLightsStatusByLocation(String location);

    public Mono<LightStatusBoundary> getSpecificLightsStatus(String id);
}
