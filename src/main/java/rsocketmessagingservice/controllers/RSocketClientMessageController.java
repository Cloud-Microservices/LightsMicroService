package rsocketmessagingservice.controllers;


import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import rsocketmessagingservice.boundaries.MessageBoundary;

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
}
