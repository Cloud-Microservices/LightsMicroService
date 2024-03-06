package lightsmicroservice.boundaries.externalBoundary;

import java.util.*;

public class MessageBoundary {

    private String messageId;
    private Date publishedTimestamp;
    private String messageType; //for example- deviceNotification
    private String summary; //what happen --> device {deviceId} has been turned on/off
    private List<ExternalReferenceBoundary> externalReferences;
    private Map<String, Object> messageDetails; // the map will be the deviceBoundary

    public MessageBoundary() {}

//    public MessageBoundary(MessageEntity entity) {
//        this.setMessageId(entity.getMessageId());
//        this.setPublishedTimestamp(entity.getPublishedTimestamp());
//        this.setMessageType(entity.getMessageType());
//        this.setSummary(entity.getSummary());
//        this.setExternalReferences(entity.getExtrenalReferences());
//    }

    public MessageBoundary(String messageType, String summary) {
        this.messageType = messageType;
        this.summary = summary;
        this.externalReferences = new ArrayList<>();
        this.messageDetails = new HashMap<>();
    }
//
//    public MessageEntity toEntity() {
//        MessageEntity rv = new MessageEntity();
//        rv.setMessageId(this.getMessageId());
//        rv.setPublishedTimestamp(this.getPublishedTimestamp());
//        rv.setMessageType(this.getMessageType());
//        rv.setSummary(this.getSummary());
//        rv.setExtrenalReferences(this.getExternalReferences());
//        rv.setMessageDetails(this.getMessageDetails());
//
//
//        return rv;
//    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
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

    public List<ExternalReferenceBoundary> getExternalReferences() {
        return externalReferences;
    }

    public void setExternalReferences(List<ExternalReferenceBoundary> externalReferences) {
        this.externalReferences = externalReferences;
    }

    public Map<String, Object> getMessageDetails() {
        return messageDetails;
    }

    public void setMessageDetails(Map<String, Object> messageDetails) {
        this.messageDetails = messageDetails;
    }

    public Date getPublishedTimestamp() {
        return publishedTimestamp;
    }

    public void setPublishedTimestamp(Date publishedTimestamp) {
        this.publishedTimestamp = publishedTimestamp;
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
