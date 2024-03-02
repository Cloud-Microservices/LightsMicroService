package lightsmicroservice.logic;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class MessagesListener {
	private ObjectMapper jackson;

	private LightsService lightsService;

	private Log logger = LogFactory.getLog(MessagesListener.class);

	public MessagesListener(LightsService lightsService) {
		this.lightsService = lightsService;
	}

	@PostConstruct
	public void init() {
		this.jackson = new ObjectMapper();
	}
	
	@Bean
	public Consumer<String> MessagesSink(){
		return stringInput->{
			this.logger.trace("*** received: " + stringInput);
		};
/*
		return stringInput->{
			try {
				this.logger.trace("*** received: " + stringInput);

				DemoMessageBoundary message = this.jackson.readValue(stringInput, DemoMessageBoundary.class);

				if (message.getMessageDetails() == null) {
					message.setMessageDetails(new HashMap<>());
				}
				message.getMessageDetails()
					.put("status", "received-from-kafka");

				DemoMessageBoundary storedMessage = this.demoService
					.store(message)
					.block();

				this.logger.info("*** stored: " + storedMessage);
			}catch (Exception e) {
				e.printStackTrace();
				this.logger.error(e);
			}
		};
 */
	}
}
