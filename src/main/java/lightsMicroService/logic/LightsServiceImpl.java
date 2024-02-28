package lightsMicroService.logic;

import lightsMicroService.Utils.Validators;
import lightsMicroService.boundaries.LightBoundary;
import lightsMicroService.boundaries.LightStatusBoundary;
import lightsMicroService.boundaries.LocationStatusBoundary;
import lightsMicroService.boundaries.StatusBoundary;
import lightsMicroService.dal.LightsCrud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
public class LightsServiceImpl implements LightsService {
    private LightsCrud lightsCrud;


    @Autowired
    public void setMessageCrud(LightsCrud lightsCrud) {
        this.lightsCrud = lightsCrud;
    }

    @Override
    public Mono<LightBoundary> createLight(LightBoundary light) {
        light.setId(null);
        Date date = new Date();
        light.setLastUpdateTimestamp(date);
        light.setRegistrationTimestamp(date);
        return Mono.just(light.toEntity())
                .flatMap(this.lightsCrud::save)
                .map(LightBoundary::new)
                .log();

    }

    @Override
    public Mono<Void> deleteLight(String id) {
        return this.lightsCrud.deleteById(id);

    }

    @Override
    public Mono<Void> deleteAll() {
        return this.lightsCrud.deleteAll();

    }

    @Override
    public Mono<LightBoundary> updateLight(LightBoundary light) {
        return lightsCrud.findById(light.getId())
                .flatMap(l -> {
                    l.setLastUpdateTimestamp(new Date());
                    if (!light.getAlias().isEmpty())
                        l.setAlias(light.getAlias());
                    if (!light.getLightType().isEmpty())
                        l.setLightType(light.getLightType());
                    if (!light.getLocation().isEmpty())
                        l.setLocation(light.getLocation());
                    return lightsCrud.save(l);

                })
                .map(LightBoundary::new)
                .log();
    }


    @Override
    public Mono<LightStatusBoundary> updateSpecificLightStatus(Mono<LightStatusBoundary> lightStatus) {
        return null;
    }


    @Override
    public Flux<LightStatusBoundary> updateLightsStatusByLocation(Mono<LocationStatusBoundary> updateLocationStatus) {
        return null;
    }

    @Override
    public Flux<LightStatusBoundary> updateAllLightsStatus(Mono<StatusBoundary> lightStatusBoundary) {
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

/**
 * @Override public Mono<Void> deleteLight(String id) {
 * return this.lightsCrud.deleteById(id);
 * }
 * @Override public Mono<Void> deleteAll() {
 * return this.lightsCrud.deleteAll();
 * }
 */
