package lightsMicroService.controllers;
import lightsMicroService.boundaries.LightBoundary;
import lightsMicroService.boundaries.LightStatusBoundary;
import lightsMicroService.logic.LightsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
        return lightsService.create(light);
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
            path = "/status/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<LightStatusBoundary> updateSpecificLightStatus(@PathVariable String id, @RequestBody LightStatusBoundary status) {
        return lightsService.updateLightStatus(id, status);
    }

    @PutMapping(path = "/status/location/{location}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<LightStatusBoundary> updateLightsStatusByLocation(@PathVariable String location, @RequestBody LocationStatusBoundary locationStatus) {
        return lightsService.updateLightsStatusByLocation(location, locationStatus);
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
        return lightsService.getSpecificLightStatus(id);
    }
}

