package lightsmicroservice.logic;

import jakarta.annotation.PostConstruct;
import lightsmicroservice.boundaries.LightBoundary;
import lightsmicroservice.boundaries.LightStatusBoundary;
import lightsmicroservice.boundaries.LocationStatusBoundary;
import lightsmicroservice.boundaries.StatusBoundary;
import lightsmicroservice.boundaries.externalBoundary.DeviceBoundary;
import lightsmicroservice.boundaries.externalBoundary.MessageBoundary;
import lightsmicroservice.dal.LightsCrud;
import lightsmicroservice.data.LightEntity;
import lightsmicroservice.logic.messages.MessagesHandler;
import lightsmicroservice.utils.Converters;
import lightsmicroservice.utils.Validators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
public class LightsServiceImpl implements LightsService {
    private LightsCrud lightsCrud;
    private RSocketRequester requester;
    private RSocketRequester.Builder requesterBuilder;
    private String rsocketHost;
    private int rsocketPort;
    private MessagesHandler messagesHandler;
    private Converters converter;

    public LightsServiceImpl(LightsCrud lightsCrud, Converters converter, MessagesHandler messagesHandler) {
        this.lightsCrud = lightsCrud;
        this.converter = converter;
        this.messagesHandler = messagesHandler;
    }

    @Autowired
    public void setRequesterBuilder(RSocketRequester.Builder requesterBuilder) {
        this.requesterBuilder = requesterBuilder;
    }

    @Value("${services.devicemanager.rsocket.host:127.0.0.1}")
    public void setRsocketHost(String rsocketHost) {
        this.rsocketHost = rsocketHost;
    }

    @Value("${services.devicemanager.rsocket.port:7071}")
    public void setRsocketPort(int rsocketPort) {
        this.rsocketPort = rsocketPort;
    }

    @PostConstruct
    public void init() {
        this.requester = this.requesterBuilder.tcp(rsocketHost, rsocketPort);
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
                .flatMap(lightBoundary ->
                {
                    DeviceBoundary deviceBoundary = this.converter.toDeviceBoundary(lightBoundary);
                    MessageBoundary messageBoundary = messagesHandler.createMessage(
                            "deviceNotification",
                            "Light device created.",
                            deviceBoundary);
                    return this.requester
                            .route("registerDevice-req-resp")
                            .data(messageBoundary)
                            .retrieveMono(MessageBoundary.class)
                            .thenReturn(lightBoundary);
                })
                .log();
    }

    @Override
    public Mono<Void> deleteLight(String id) {
        return this.requester
                .route("deleteDeviceById-{id}-fnf", id)
                .send()
                .then(this.lightsCrud.deleteById(id));
    }

    @Override
    public Mono<Void> deleteAll() {
        return this.lightsCrud.findAll()
                .flatMap(lightEntity ->
                        this.requester
                                .route("deleteDeviceById-{id}-fnf", lightEntity.getId())
                                .send()
                                .thenReturn(lightEntity)
                )
                .then(this.lightsCrud.deleteAll());
    }

    @Override
    public Mono<LightBoundary> updateLight(LightBoundary light) {
        return lightsCrud.findById(light.getId())
                .flatMap(lightEntity -> {
                    lightEntity.setLastUpdateTimestamp(new Date());
                    if (light.getAlias() != null && !light.getAlias().isEmpty())
                        lightEntity.setAlias(light.getAlias());
                    if (light.getLightType() != null && !light.getLightType().isEmpty())
                        lightEntity.setLightType(light.getLightType());
                    if (light.getLocation() != null && !light.getLocation().isEmpty())
                        lightEntity.setLocation(light.getLocation());
                    return lightsCrud.save(lightEntity);
                })
                .flatMap(lightEntity -> {
                    DeviceBoundary deviceBoundary = this.converter.toDeviceBoundary(lightEntity);
                    MessageBoundary messageBoundary = messagesHandler.createMessage(
                            "Light",
                            "Light updated",
                            deviceBoundary);

                    LightBoundary lightBoundary = new LightBoundary(lightEntity);
                    return this.requester
                            .route("updateDevice-{id}-fnf", lightBoundary.getId())
                            .data(messageBoundary)
                            .retrieveMono(MessageBoundary.class)
                            .thenReturn(lightBoundary);
                })
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
                .flatMap(LightStatus ->
                     this.requester
                            .route("updateDeviceStatus-{id}-fnf", LightStatus.getId())
                            .data(LightStatus.getStatus())
                            .send()
                            .thenReturn(LightStatus)
                )
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
                .flatMap(LightStatus ->
                        this.requester
                                .route("updateDeviceStatus-{id}-fnf", LightStatus.getId())
                                .data(LightStatus.getStatus())
                                .send()
                                .thenReturn(LightStatus)
                )
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
                .flatMap(LightStatus ->
                        this.requester
                                .route("updateDeviceStatus-{id}-fnf", LightStatus.getId())
                                .data(LightStatus.getStatus())
                                .send()
                                .thenReturn(LightStatus)
                )
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
            if (newStatus.getCurrentPowerInWatts() != null && newStatus.getCurrentPowerInWatts() > 0) {
                lightEntity.getStatus().setCurrentPowerInWatts(newStatus.getCurrentPowerInWatts());
            }
        }
        return lightEntity;
    }
}