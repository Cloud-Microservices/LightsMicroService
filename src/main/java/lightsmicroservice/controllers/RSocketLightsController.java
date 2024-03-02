package lightsmicroservice.controllers;

import lightsmicroservice.boundaries.LightBoundary;
import lightsmicroservice.boundaries.LightStatusBoundary;
import lightsmicroservice.boundaries.LocationStatusBoundary;
import lightsmicroservice.boundaries.StatusBoundary;
import lightsmicroservice.logic.LightsService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
public class RSocketLightsController {
    private LightsService lightsService;
    private Log logger = LogFactory.getLog(RSocketLightsController.class);

    @Autowired
    public RSocketLightsController(LightsService lightsService) {
        this.lightsService = lightsService;
    }

    //java -jar rsc-0.9.1.jar --request --route=create-light-req-resp --data="{\"id\":\"65e341cc86ae3d68fbc448bb\", \"lightType\":\"led\", \"alias\":\"light1_guestRoom\", \"location\":\"guestRoom\"}" --debug tcp://localhost:7007
    @MessageMapping("create-light-req-resp")
    public Mono<LightBoundary> createLight(
            @Payload LightBoundary light) {
        this.logger.debug("create-light-req-resp");
        return lightsService.createLight(light);
    }
    //java -jar rsc-0.9.1.jar --fnf --route=delete-light-fnf --data="65e341cc86ae3d68fbc448bb" --debug tcp://localhost:7007
    @MessageMapping("delete-light-fnf")
    public Mono<Void> deleteLight(@Payload String id) {
        this.logger.debug("delete-light-fnf");
        return lightsService.deleteLight(id);
    }

    //java -jar rsc-0.9.1.jar --fnf --route=delete-all-light-fnf --debug tcp://localhost:7007
    @MessageMapping("delete-all-light-fnf")
    public Mono<Void> deleteAll() {
        this.logger.debug("delete-all-light-fnf");
        return lightsService.deleteAll();
    }

//    //java -jar rsc-0.9.1.jar --request --route=update-light-req-resp --data="{\"id\":\"65e2ff0d5e136f12b3c27703\", \"lightType\":\"led\", \"alias\": \"light1_guestRoom\", \"location\":\"guestRoom\"}" --debug tcp://localhost:7007
    @MessageMapping("update-light-req-resp")
    public Mono<LightBoundary> updateLight(
            @Payload LightBoundary light) {
        this.logger.debug("update-light-req-resp");
        return lightsService.updateLight(light);
    }

    // java -jar rsc-0.9.1.jar --request --route=update-light-status-specific-req-resp --data="{\"id\":\"65e2ff0d5e136f12b3c27703\", \"status\":{\"isOn\":true, \"brightness\":70,\"colorRGB\":[130,0,55]}}" --debug tcp://localhost:7007
    @MessageMapping("update-light-status-specific-req-resp")
    public Mono<LightStatusBoundary> updateSpecificLightStatus(
            @Payload LightStatusBoundary lightStatus) {
        this.logger.debug("update-light-status-specific-req-resp");
        return lightsService.updateSpecificLightStatus(lightStatus);
    }

    // java -jar rsc-0.9.1.jar --stream --route=update-light-status-by-location-req-stream --data="{\"location\":\"kitchen\", \"status\":{\"isOn\":true, \"brightness\":75,\"colorRGB\":[250,20,0]}}" --debug tcp://localhost:7007
    @MessageMapping("update-light-status-by-location-req-stream")
    public Flux<LightStatusBoundary> updateLightsStatusByLocation(
            @Payload LocationStatusBoundary updateLocationStatus) {
        this.logger.debug("update-light-status-by-location-req-stream");
        return lightsService.updateLightsStatusByLocation(updateLocationStatus);
    }

    // java -jar rsc-0.9.1.jar --stream --route=update-light-status-all-req-stream --data="{\"isOn\":true, \"brightness\":75,\"colorRGB\":[120,205,83]}" --debug tcp://localhost:7007
    @MessageMapping("update-light-status-all-req-stream")
    public Flux<LightStatusBoundary> updateAllLightsStatus(
            @Payload StatusBoundary statusBoundary) {
        this.logger.debug("update-light-status-all-req-stream");
        return lightsService.updateAllLightsStatus(statusBoundary);
    }


    // java -jar rsc-0.9.1.jar --request --route=get-light-by-id-req-resp --data="65e2fefa5e136f12b3c27701" --debug tcp://localhost:7007
    @MessageMapping("get-light-by-id-req-resp")
    public Mono<LightBoundary> getLightById(String id) {
        this.logger.debug("get-light-by-id-req-resp");
        return lightsService.getLightById(id);
    }

    // java -jar rsc-0.9.1.jar --stream --route=get-all-lights-req-stream --debug tcp://localhost:7007
    @MessageMapping("get-all-lights-req-stream")
    public Flux<LightBoundary> getAllLights() {
        this.logger.debug("get-all-lights-req-stream");
        return lightsService.getAllLights();
    }

    // java -jar rsc-0.9.1.jar --stream --route=get-all-lights-status-req-stream --debug tcp://localhost:7007
    @MessageMapping("get-all-lights-status-req-stream")
    public Flux<LightStatusBoundary> getAllLightsStatus() {
        this.logger.debug("get-all-lights-status-req-stream");
        return lightsService.getAllLightsStatus();
    }

    // java -jar rsc-0.9.1.jar --stream --route=get-lights-status-by-location-req-stream --data="bathroom" --debug tcp://localhost:7007
    @MessageMapping("get-lights-status-by-location-req-stream")
    public Flux<LightStatusBoundary> getAllLightsStatusByLocation(String location) {
        this.logger.debug("get-lights-status-by-location-req-stream");
        return lightsService.getAllLightsStatusByLocation(location);
    }

    // java -jar rsc-0.9.1.jar --request --route=get-specific-light-status-req-resp --data="65e2fefa5e136f12b3c27701" --debug tcp://localhost:7007
    @MessageMapping("get-specific-light-status-req-resp")
    public Mono<LightStatusBoundary> getSpecificLightStatus(String id) {
        this.logger.debug("get-specific-light-status-req-resp");
        return lightsService.getSpecificLightsStatus(id);
    }
}
