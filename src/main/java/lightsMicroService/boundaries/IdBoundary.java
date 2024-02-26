package lightsMicroService.boundaries;

public class IdBoundary {
    private String messageId;

    public IdBoundary(String messageId) {
        this.messageId = messageId;
    }

    public IdBoundary() {
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }
}
