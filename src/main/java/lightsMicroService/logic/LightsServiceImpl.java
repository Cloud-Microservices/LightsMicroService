package lightsMicroService.logic;

import lightsMicroService.Exception.NotFoundException;
import lightsMicroService.Utils.Validators;
import lightsMicroService.boundaries.LightBoundary;
import lightsMicroService.boundaries.LightStatusBoundary;
import lightsMicroService.boundaries.LocationStatusBoundary;
import lightsMicroService.boundaries.StatusBoundary;
import lightsMicroService.dal.LightStatusCrud;
import lightsMicroService.dal.LightsCrud;
import lightsMicroService.data.LightStatusEntity;
import lightsMicroService.data.StatusEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
public class LightsServiceImpl implements LightsService {
    private LightsCrud lightsCrud;

    private LightStatusCrud lightStatusCrud;


    @Autowired
    public void setMessageCrud(LightsCrud lightsCrud, LightStatusCrud lightStatusCrud) {
        this.lightsCrud = lightsCrud;
        this.lightStatusCrud = lightStatusCrud;
    }

    @Override
    public Mono<LightBoundary> createLight(LightBoundary light) {
        light.setId(null);
        Date date = new Date();
        light.setLastUpdateTimestamp(date);
        light.setRegistrationTimestamp(date);

        return Mono.just(light.toEntity())
                .flatMap(this.lightsCrud::save)
                .flatMap(lightEntity -> {
                    LightStatusEntity lightStatusEntity = new LightStatusEntity(lightEntity.getId(),new StatusEntity(100,new int[]{255,255,255},false));
                    return lightStatusCrud.save(lightStatusEntity)
                            .thenReturn(lightEntity);
                })
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


    //TODO: Need to ask Eyal how to implement error
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
    public Mono<LightStatusBoundary> updateSpecificLightStatus(LightStatusBoundary lightStatus) {
        //TODO: need to check how to use kafka
        return lightStatusCrud.findById(lightStatus.getId())
                .flatMap(l -> {
                    if (lightStatus.getStatus() != null) {
                        if (lightStatus.getStatus().getIsOn() != null) {
                            l.getStatus().setIsOn(lightStatus.getStatus().getIsOn());
                        }
                        if (lightStatus.getStatus().getBrightness() != null) {
                            l.getStatus().setBrightness(lightStatus.getStatus().getBrightness());
                        }
                        if (lightStatus.getStatus().getColorRGB() != null) {
                            l.getStatus().setColorRGB(lightStatus.getStatus().getColorRGB());
                        }
                    }
                    return lightStatusCrud.save(l);

                })
                .map(LightStatusBoundary::new)
                .log();
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
