package lightsmicroservice.logic;

import lightsmicroservice.boundaries.LightBoundary;
import lightsmicroservice.boundaries.LightStatusBoundary;
import lightsmicroservice.boundaries.LocationStatusBoundary;
import lightsmicroservice.boundaries.StatusBoundary;
import lightsmicroservice.dal.LightsCrud;
import lightsmicroservice.data.LightEntity;
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
        /*
         TODO:  need to check id is found
                exception if id not found
         */
        return lightsCrud.findById(light.getId())
                .flatMap(lightEntity -> {
                    lightEntity.setLastUpdateTimestamp(new Date());
                    if (!light.getAlias().isEmpty())
                        lightEntity.setAlias(light.getAlias());
                    if (!light.getLightType().isEmpty())
                        lightEntity.setLightType(light.getLightType());
                    if (!light.getLocation().isEmpty())
                        lightEntity.setLocation(light.getLocation());
                    return lightsCrud.save(lightEntity);
                })
                .map(LightBoundary::new)
                .log();
    }

    @Override
    public Mono<LightStatusBoundary> updateSpecificLightStatus(LightStatusBoundary lightStatus) {
        /*
         TODO:  need to check id is found
                exception if id not found
         */
        return lightsCrud.findById(lightStatus.getId())
                .flatMap(lightEntity -> {
                    LightEntity newLightEntity = updateLightStatus(lightEntity, lightStatus.getStatus());
                    return lightsCrud.save(newLightEntity);
                })
                .map(LightStatusBoundary::new)
                .log();
    }

    @Override
    public Flux<LightStatusBoundary> updateLightsStatusByLocation(LocationStatusBoundary locationStatusBoundary) {
        /*
         TODO:  need to check id is found
                exception if id not found
         */
        return lightsCrud.findAllByLocation(locationStatusBoundary.getLocation())
                .flatMap(lightEntity -> {
                    LightEntity newLightEntity = updateLightStatus(lightEntity, locationStatusBoundary.getStatus());
                    return lightsCrud.save(newLightEntity);
                })
                .map(LightStatusBoundary::new)
                .log();
    }

    @Override
    public Flux<LightStatusBoundary> updateAllLightsStatus(StatusBoundary statusBoundary) {
        /*
         TODO:  need to check id is found
                exception if id not found
         */
        return lightsCrud.findAll()
                .flatMap(lightEntity -> {
                    LightEntity newLightEntity = updateLightStatus(lightEntity, statusBoundary);
                    return lightsCrud.save(newLightEntity);
                })
                .map(LightStatusBoundary::new)
                .log();
    }

    @Override
    public Mono<LightBoundary> getLightById(String id) {
        /*
         TODO:  need to check id is found
                exception if id not found
         */
        return lightsCrud.findById(id)
                .map(LightBoundary::new)
                .log();
    }

    @Override
    public Flux<LightBoundary> getAllLights() {
        return lightsCrud.findAll()
                .map(LightBoundary::new)
                .log();
    }

    @Override
    public Flux<LightStatusBoundary> getAllLightsStatus() {
        return lightsCrud.findAll()
                .map(LightStatusBoundary::new)
                .log();
    }

    @Override
    public Flux<LightStatusBoundary> getAllLightsStatusByLocation(String location) {
        return lightsCrud.findAllByLocation(location)
                .map(LightStatusBoundary::new)
                .log();
    }

    @Override

    public Mono<LightStatusBoundary> getSpecificLightsStatus(String id) {
        /*
         TODO:  need to check id is found
                exception if id not found
         */
        return lightsCrud.findById(id)
                .map(LightStatusBoundary::new)
                .log();
    }

    private LightEntity updateLightStatus(LightEntity lightEntity, StatusBoundary newStatus) {
        /*
        TODO:   check if there is a change in the status
                and decide what to do and who to notice
         */
        if (newStatus != null) {
            if (newStatus.getIsOn() != null) {
                lightEntity.getStatus().setIsOn(newStatus.getIsOn());
            }
            if (newStatus.getBrightness() != null) {
                lightEntity.getStatus().setBrightness(newStatus.getBrightness());
            }
            if (newStatus.getColorRGB() != null) {
                lightEntity.getStatus().setColorRGB(newStatus.getColorRGB());
            }
        }
        return lightEntity;
    }
}
