//package lightsMicroService.controllers;
//import lightsMicroService.boundaries.LightBoundary;
//import lightsMicroService.logic.LightsService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//import reactor.core.publisher.Flux;
//import reactor.core.publisher.Mono;
//
//@RestController
//@RequestMapping("/lights")
//public class RestLightsController {
//
//
//    private LightsService lightsService;
//
//    @Autowired
//    public RestLightsController(LightsService lightsService) {
//        this.lightsService = lightsService;
//    }
//
//    // Create light
//    @PostMapping
//    public Mono<LightBoundary> createLight(@RequestBody LightBoundary lightBoundary) {
//        return lightsService.createLight(lightBoundary);
//    }
//
//    // Delete light
//    @DeleteMapping("/{id}")
//    public Mono<Void> deleteLight(@PathVariable String id) {
//        return lightsService.deleteLight(id);
//    }
//
//    // Delete all lights
//    @DeleteMapping
//    public Mono<Void> deleteAllLights() {
//        return lightsService.deleteAllLights();
//    }
//
//    // Update light
//    @PutMapping
//    public Flux<LightBoundary> updateLight(@RequestBody Flux<LightBoundary> lightBoundaries) {
//        return lightsService.updateLight(lightBoundaries);
//    }
//
//    // Update specific light status
//    @PutMapping("/status/{id}")
//    public Mono<LightStatusBoundary> updateSpecificLightStatus(@PathVariable String id, @RequestBody LightStatusBoundary lightStatusBoundary) {
//        return lightsService.updateSpecificLightStatus(id, lightStatusBoundary);
//    }
//
//    // Update lights status in location
//    @PutMapping("/status/location/{location}")
//    public Flux<LightStatusBoundary> updateLightStatusByLocation(@PathVariable String location, @RequestBody LocationStatusBoundary locationStatusBoundary) {
//        return lightsService.updateLightStatusByLocation(location, locationStatusBoundary);
//    }
//
//    // Update All lights status
//    @PutMapping("/status")
//    public Flux<LightStatusBoundary> updateAllLightsStatus(@RequestBody StatusBoundary statusBoundary) {
//        return lightsService.updateAllLightsStatus(statusBoundary);
//    }
//
//    // Get light by ID
//    @GetMapping("/{id}")
//    public Mono<LightBoundary> getLightById(@PathVariable String id) {
//        return lightsService.getLightById(id);
//    }
//
//    // Get all lights
//    @GetMapping
//    public Flux<LightBoundary> getAllLights() {
//        return lightsService.getAllLights();
//    }
//
//    // Get all lights status
//    @GetMapping("/status")
//    public Flux<LightStatusBoundary> getAllLightsStatus() {
//        return lightsService.getAllLightsStatus();
//    }
//
//    // Get lights status in a specific location
//    @GetMapping("/status/location/{location}")
//    public Flux<LightStatusBoundary> getLightsStatusByLocation(@PathVariable String location) {
//        return lightsService.getLightsStatusByLocation(location);
//    }
//
//    // Get specific light status
//    @GetMapping("/status/{id}")
//    public Mono<LightStatusBoundary> getSpecificLightStatus(@PathVariable String id) {
//        return lightsService.getSpecificLightStatus(id);
//    }
//}
//
