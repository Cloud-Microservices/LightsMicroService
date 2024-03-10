package lightsmicroservice.boundaries.externalBoundary;

import java.util.*;

public class MessageBoundary {

    private String messageId;
    private Date publishedTimestamp;
    private String messageType;
    private String summary;
    private List<ExternalReferenceBoundary> externalReferences;
    private Map<String, DeviceBoundary> messageDetails;

    public MessageBoundary() {}

    public MessageBoundary(String messageType, String summary) {
        this.messageType = messageType;
        this.summary = summary;
        this.externalReferences = new ArrayList<>();
        this.messageDetails = new HashMap<>();
    }

    public String getMessageId() {
        return messageId;
    }

    public MessageBoundary setMessageId(String messageId) {
        this.messageId = messageId;
        return this;
    }

    public String getMessageType() {
        return messageType;
    }

    public MessageBoundary setMessageType(String messageType) {
        this.messageType = messageType;
        return this;
    }

    public String getSummary() {
        return summary;
    }

    public MessageBoundary setSummary(String summary) {
        this.summary = summary;
        return this;

    }

    public List<ExternalReferenceBoundary> getExternalReferences() {
        return externalReferences;
    }

    public MessageBoundary setExternalReferences(List<ExternalReferenceBoundary> externalReferences) {
        this.externalReferences = externalReferences;
        return this;
    }

    public Map<String, DeviceBoundary> getMessageDetails() {
        return messageDetails;
    }

    public MessageBoundary setMessageDetails(Map<String, DeviceBoundary> messageDetails) {
        this.messageDetails = messageDetails;
        return this;
    }

    public Date getPublishedTimestamp() {
        return publishedTimestamp;
    }

    public MessageBoundary setPublishedTimestamp(Date publishedTimestamp) {
        this.publishedTimestamp = publishedTimestamp;
        return this;
    }

    @Override
    public String toString() {
        return "MessageBoundary{" +
                "messageId='" + messageId + '\'' +
                ", publishedTimestamp=" + publishedTimestamp +
                ", messageType='" + messageType + '\'' +
                ", summary='" + summary + '\'' +
                ", extrenalReferences=" + externalReferences +
                ", messageDetails=" + messageDetails +
                '}';
    }
}
