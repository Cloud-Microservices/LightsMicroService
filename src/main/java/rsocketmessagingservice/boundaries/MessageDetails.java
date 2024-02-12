package rsocketmessagingservice.boundaries;

import java.util.HashMap;
import java.util.Map;

public class MessageDetails {


    private Map<String, Object> messageDetails;


    public MessageDetails(Map<String, Object> messageDetails) {
        this.messageDetails = messageDetails;
    }

    public MessageDetails() {
    }

    public Map<String, Object> getMessageDetails() {
        return messageDetails;
    }

    public void setMessageDetails(Map<String, Object> messageDetails) {
        this.messageDetails = messageDetails;
    }

    @Override
    public String toString() {
        return "MessageDetails{" +
                "messageDetails=" + messageDetails +
                '}';
    }
}
