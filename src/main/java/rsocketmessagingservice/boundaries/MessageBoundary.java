package rsocketmessagingservice.boundaries;

import rsocketmessagingservice.data.MessageEntity;

import java.util.*;

public class MessageBoundary {

    private String messageId;
    private Date publishedTimestamp;
    private String messageType;
    private String summary;
    private List<ExternalReferenceBoundary> extrenalReferences;

    private Map<String, Object> messageDetails;

    public MessageBoundary() {
    }

    public MessageBoundary(MessageEntity entity) {
        this.setMessageId(entity.getMessageId());
        this.setPublishedTimestamp(entity.getPublishedTimestamp());
        this.setMessageType(entity.getMessageType());
        this.setSummary(entity.getSummary());
        this.setExtrenalReferences(entity.getExtrenalReferences());
        this.setMessageDetails(entity.getMessageDetails());


    }

    public MessageBoundary(String messageType, String summary) {
        this.messageType = messageType;
        this.summary = summary;
        this.extrenalReferences = new ArrayList<>();
        this.messageDetails = new HashMap<>();
    }

    public MessageEntity toEntity() {
        MessageEntity rv = new MessageEntity();
        rv.setMessageId(this.getMessageId());
        rv.setPublishedTimestamp(this.getPublishedTimestamp());
        rv.setMessageType(this.getMessageType());
        rv.setSummary(this.getSummary());
        rv.setExtrenalReferences(this.getExtrenalReferences());
        rv.setMessageDetails(this.getMessageDetails());


        return rv;
    }

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
                ", extrenalReferences=" + extrenalReferences +
                ", messageDetails=" + messageDetails +
                '}';
    }
}
