package lightsMicroService.data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lightsMicroService.boundaries.ExternalReferenceBoundary;

import java.util.*;

@Document(collection = "MESSAGES")

public class MessageEntity {

    @Id private String messageId;
    private Date publishedTimestamp;
    private String messageType;
    private String summary;
    private List<ExternalReferenceBoundary> extrenalReferences;

    private Map<String,Object> messageDetails;

    public MessageEntity() {
    }

    public MessageEntity(String messageId, Date publishedTimestamp, String messageType, String summary) {
        this.messageId = messageId;
        this.publishedTimestamp = publishedTimestamp;
        this.messageType = messageType;
        this.summary = summary;
        this.extrenalReferences = new ArrayList<>();
        this.messageDetails = new HashMap<>();
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public Date getPublishedTimestamp() {
        return publishedTimestamp;
    }

    public void setPublishedTimestamp(Date publishedTimestamp) {
        this.publishedTimestamp = publishedTimestamp;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public List<ExternalReferenceBoundary> getExtrenalReferences() {
        return extrenalReferences;
    }

    public void setExtrenalReferences(List<ExternalReferenceBoundary> extrenalReferences) {
        this.extrenalReferences = extrenalReferences;
    }

    public Map<String, Object> getMessageDetails() {
        return messageDetails;
    }

    public void setMessageDetails(Map<String, Object> messageDetails) {
        this.messageDetails = messageDetails;
    }
}
