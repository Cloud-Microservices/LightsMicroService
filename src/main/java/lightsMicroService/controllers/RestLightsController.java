package lightsMicroService.controllers;

import lightsMicroService.boundaries.LightBoundary;
import lightsMicroService.boundaries.LightStatusBoundary;
import lightsMicroService.boundaries.LocationStatusBoundary;
import lightsMicroService.boundaries.StatusBoundary;
import lightsMicroService.logic.LightsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/lights")
public class RestLightsController {

    private final LightsService lightsService;

    @Autowired
    public RestLightsController(LightsService lightsService) {
        this.lightsService = lightsService;
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<LightBoundary> createLight(@RequestBody LightBoundary light) {
        return lightsService.createLight(light);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteLight(@PathVariable String id) {
        return lightsService.deleteLight(id);
    }

    @DeleteMapping
    public Mono<Void> deleteAllLights() {
        return lightsService.deleteAll();
    }

    @PutMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<LightBoundary> updateLight(@RequestBody LightBoundary light) {
        return lightsService.updateLight(light);
    }

    @PutMapping(
            path = "/status",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<LightStatusBoundary> updateSpecificLightStatus(@RequestBody LightStatusBoundary lightStatusBoundary) {

        return lightsService.updateSpecificLightStatus(lightStatusBoundary);
    }

    @PutMapping(path = "/status/all", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<LightStatusBoundary> updateAllLightsStatus(@RequestBody Mono<StatusBoundary> statusBoundary) {

        return lightsService.updateAllLightsStatus(statusBoundary);
    }

    @PutMapping(path = "/status/location", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<LightStatusBoundary> updateLightsStatusByLocation(@RequestBody Mono<LocationStatusBoundary> locationStatus) {

        return lightsService.updateLightsStatusByLocation(locationStatus);
    }


    @GetMapping("/{id}")
    public Mono<LightBoundary> getLightById(@PathVariable String id) {
        return lightsService.getLightById(id);
    }

    @GetMapping
    public Flux<LightBoundary> getAllLights() {
        return lightsService.getAllLights();
    }

    @GetMapping("/status")
    public Flux<LightStatusBoundary> getAllLightsStatus() {
        return lightsService.getAllLightsStatus();
    }

    @GetMapping("/status/location/{location}")
    public Flux<LightStatusBoundary> getAllLightsStatusByLocation(@PathVariable String location) {
        return lightsService.getAllLightsStatusByLocation(location);
    }

    @GetMapping("/status/{id}")
    public Mono<LightStatusBoundary> getSpecificLightStatus(@PathVariable String id) {
        return lightsService.getSpecificLightsStatus(id);
    }
}

