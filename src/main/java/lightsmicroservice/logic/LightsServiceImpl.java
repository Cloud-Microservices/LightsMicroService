package lightsmicroservice.logic;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lightsmicroservice.boundaries.LightBoundary;
import lightsmicroservice.boundaries.LightStatusBoundary;
import lightsmicroservice.boundaries.LocationStatusBoundary;
import lightsmicroservice.boundaries.StatusBoundary;
import lightsmicroservice.dal.LightsCrud;
import lightsmicroservice.data.LightEntity;
import lightsmicroservice.utils.Validators;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
public class LightsServiceImpl implements LightsService {
    private LightsCrud lightsCrud;

    private StreamBridge kafka;

    private ObjectMapper jackson;

    private String targetTopic;

    public LightsServiceImpl(LightsCrud lightsCrud, StreamBridge kafka) {
        this.lightsCrud = lightsCrud;
        this.kafka = kafka;
    }

    @PostConstruct
    public void init() {
        this.jackson = new ObjectMapper();
    }

    @Value("${target.topic.name:anyTopic}")
    public void setTargetTopic(String targetTopic) {
        this.targetTopic = targetTopic;
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
                .flatMap(lightEntity -> {
                    lightEntity.setLastUpdateTimestamp(new Date());
                    if (light.getAlias()!= null && !light.getAlias().isEmpty())
                        lightEntity.setAlias(light.getAlias());
                    if (light.getLightType()!= null && !light.getLightType().isEmpty())
                        lightEntity.setLightType(light.getLightType());
                    if (light.getLocation()!= null && !light.getLocation().isEmpty())
                        lightEntity.setLocation(light.getLocation());
                    return lightsCrud.save(lightEntity);
                })
                .map(LightBoundary::new)
                .log();
    }

    @Override
    public Mono<LightStatusBoundary> updateSpecificLightStatus(LightStatusBoundary lightStatus) {
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
        return lightsCrud.findById(id)
                .map(LightStatusBoundary::new)
                .log();
    }

    @Override
    public Flux<LightBoundary> getLightsByStatusIsOn(Boolean isOn) {
        return lightsCrud.findAllByStatus_isOn(isOn)
                .map(LightBoundary::new)
                .log();
    }

    private LightEntity updateLightStatus(LightEntity lightEntity, StatusBoundary newStatus) {
        if (newStatus != null) {
            if (newStatus.getIsOn() != null) {
                lightEntity.getStatus().setIsOn(newStatus.getIsOn());
            }
            if (Validators.isBrightnessValid(newStatus.getBrightness())) {
                lightEntity.getStatus().setBrightness(newStatus.getBrightness());
            }
            if (Validators.isColorRGBValid(newStatus.getColorRGB())) {
                lightEntity.getStatus().setColorRGB(newStatus.getColorRGB());
            }
        }
        return lightEntity;
    }
}
