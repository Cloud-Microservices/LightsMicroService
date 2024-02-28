package lightsMicroService.controllers;

import lightsMicroService.boundaries.LightBoundary;
import lightsMicroService.boundaries.LightStatusBoundary;
import lightsMicroService.boundaries.LocationStatusBoundary;
import lightsMicroService.logic.LightsService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
public class RSocketLightsController {
    private LightsService lightsService;
    private Log logger = LogFactory.getLog(RSocketLightsController.class);

    @MessageMapping("create-light-req-resp")
    public Mono<LightBoundary> createLight(
            @Payload LightBoundary light) {
        this.logger.debug("create-light-req-resp");
        return lightsService.createLight(light);
    }

    // java -jar rsc-0.9.1.jar --fnf --route=clear-messages-fnf --debug tcp://localhost:7001
    @MessageMapping("delete-light-req-resp")
    public Mono<Void> deleteLight(@Payload String id) {
        this.logger.debug("delete-light-req-resp");
        return lightsService.deleteLight(id);
    }

    @MessageMapping("delete-all-light-fnf")
    public Mono<Void> deleteAll() {
        this.logger.debug("delete-all-light-fnf");
        return lightsService.deleteAll();
    }

    @MessageMapping("update-light-channel")
    public Flux<LightBoundary> updateLights(
            Flux<LightBoundary> lights) {
        this.logger.debug("update-light-channel");
        return lightsService.updateLights(lights);
    }

    @MessageMapping("update-light-status-specific-channel")
    public Flux<LightStatusBoundary> updateLightsStatus(
            Flux<LightStatusBoundary> lightStatus) {
        this.logger.debug("update-light-status-specific-channel");
        return lightsService.updateLightsStatus(lightStatus);
    }

    @MessageMapping("update-light-status-by-location-req-stream")
    public Flux<LightStatusBoundary> updateLightsStatusByLocation(
            Mono<LocationStatusBoundary> updateLocationStatus) {
        this.logger.debug("update-light-status-by-location-req-stream");
        return lightsService.updateLightsStatusByLocation(updateLocationStatus);
    }

    @MessageMapping("update-light-status-all-req-stream")
    public Flux<LightStatusBoundary> updateAllLightsStatus(
            Mono<LightStatusBoundary> lightStatusBoundary) {
        this.logger.debug("update-light-status-all-req-stream");
        return lightsService.updateAllLightsStatus(lightStatusBoundary);
    }

    @MessageMapping("get-light-by-id-req-resp")
    public Mono<LightBoundary> getLightById(String id) {
        this.logger.debug("get-light-by-id-req-resp");
        return lightsService.getLightById(id);
    }

    @MessageMapping("get-all-lights-req-stream")
    public Flux<LightBoundary> getAllLights() {
        this.logger.debug("get-all-lights-req-stream");
        return lightsService.getAllLights();
    }

    @MessageMapping("get-all-lights-status-req-stream")
    public Flux<LightStatusBoundary> getAllLightsStatus() {
        this.logger.debug("get-all-lights-status-req-stream");
        return lightsService.getAllLightsStatus();
    }

    @MessageMapping("get-lights-status-by-location-req-stream")
    public Flux<LightStatusBoundary> getAllLightsStatusByLocation(String location) {
        this.logger.debug("get-lights-status-by-location-req-stream");
        return lightsService.getAllLightsStatusByLocation(location);
    }

    @MessageMapping("get-specific-light-status-req-resp")
    public Mono<LightStatusBoundary> getSpecificLightStatus(String id) {
        this.logger.debug("get-specific-light-status-req-resp");
        return lightsService.getSpecificLightsStatus(id);
    }
}
