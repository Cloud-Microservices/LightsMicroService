package lightsMicroService.logic;

import lightsMicroService.boundaries.LightBoundary;
import lightsMicroService.boundaries.LightStatusBoundary;
import lightsMicroService.boundaries.LocationStatusBoundary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
public class LightsServiceImpl implements LightsService {
    private LightsService lightsService;

    @Autowired
    public void setMessageCrud(LightsService lightsService) {
        this.lightsService = lightsService;
    }

    @Override
    public Mono<LightBoundary> createLight(LightBoundary light) {
        light.setId(null);
        Date date = new Date();
        light.setLastUpdateTimestamp(date);
        light.setRegistrationTimestamp(date);
        return null;

    }

    @Override
    public Mono<Void> deleteLight(String id) {
        return null;
    }

    @Override
    public Mono<Void> deleteAll() {
        return null;
    }

    @Override
    public Flux<LightBoundary> updateLights(Flux<LightBoundary> lights) {
        return null;
    }

    @Override
    public Flux<LightStatusBoundary> updateLightsStatus(Flux<LightStatusBoundary> lightStatus) {
        return null;
    }

    @Override
    public Flux<LightStatusBoundary> updateLightsStatusByLocation(Mono<LocationStatusBoundary> updateLocationStatus) {
        return null;
    }

    @Override
    public Flux<LightStatusBoundary> updateAllLightsStatus(Mono<LightStatusBoundary> lightStatusBoundary) {
        return null;
    }

    @Override
    public Mono<LightBoundary> getLightById(String id) {
        return null;
    }

    @Override
    public Flux<LightBoundary> getAllLights() {
        return null;
    }

    @Override
    public Flux<LightStatusBoundary> getAllLightsStatus() {
        return null;
    }

    @Override
    public Flux<LightStatusBoundary> getAllLightsStatusByLocation(String location) {
        return null;
    }

    @Override
    public Mono<LightStatusBoundary> getSpecificLightsStatus(String id) {
        return null;
    }
}
