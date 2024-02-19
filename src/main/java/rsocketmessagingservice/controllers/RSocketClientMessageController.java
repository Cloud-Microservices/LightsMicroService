package rsocketmessagingservice.controllers;


import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import rsocketmessagingservice.boundaries.IdBoundary;
import rsocketmessagingservice.boundaries.MessageBoundary;

import java.util.List;

@RestController
@RequestMapping(path = "/rsocket/messages")
public class RSocketClientMessageController {
    private RSocketRequester requester;
    private RSocketRequester.Builder requesterBuilder;
    private String rsocketHost;
    private int rsocketPort;

    @Autowired
    public void setRequesterBuilder(RSocketRequester.Builder requesterBuilder) {
        this.requesterBuilder = requesterBuilder;
    }

    @Value("${demoapp.client.rsocket.host:127.0.0.1}")
    public void setRsocketHost(String rsocketHost) {
        this.rsocketHost = rsocketHost;
    }

    @Value("${demoapp.client.rsocket.port:7000}")
    public void setRsocketPort(int rsocketPort) {
        this.rsocketPort = rsocketPort;
    }

    @PostConstruct
    public void init() {
        this.requester = this.requesterBuilder
                .tcp(rsocketHost, rsocketPort);

    }
    @PostMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public Mono<MessageBoundary> create(
            @RequestBody MessageBoundary message) {
        System.err.println("invoking: publish-message-req-resp");
        return this.requester
                .route("publish-message-req-resp")
                .data(message)
                .retrieveMono(MessageBoundary.class)
                .log();
    }

    // Testing 'getAll-req-stream'
    @GetMapping(path = "/getAll",
            produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<MessageBoundary> getAllMessages() {
        return this.requester
                .route("getAll-req-stream")
                .retrieveFlux(MessageBoundary.class)
                .log();
    }

    // Testing 'getMessagesByIds-channel'



    @GetMapping(path = "/getByIds", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<MessageBoundary> getMessagesByIds(@RequestParam("ids") List<String> ids) {
        Flux<IdBoundary> idFlux = Flux.fromIterable(ids)
                .map(IdBoundary::new);

        return this.requester
                .route("getMessagesByIds-channel")
                .data(idFlux)
                .retrieveFlux(MessageBoundary.class)
                .log();
    }



    // Testing 'deleteAll-fire-and-forget'
    @DeleteMapping(path = "/deleteAll")
    public Mono<Void> deleteAllMessages() {
        return this.requester
                .route("deleteAll-fire-and-forget")
                .send();
    }
}





