package rsocketmessagingservice.boundaries;

public class ExternalReferenceBoundary {
    private String service;
    private String extrenalServiceId;

    public ExternalReferenceBoundary(String service, String extrenalServiceId) {
        this.service = service;
        this.extrenalServiceId = extrenalServiceId;
    }

    public ExternalReferenceBoundary() {
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getExtrenalServiceId() {
        return extrenalServiceId;
    }

    public void setExtrenalServiceId(String extrenalServiceId) {
        this.extrenalServiceId = extrenalServiceId;
    }

    @Override
    public String toString() {
        return "ExternalReferenceBoundary{" +
                "service='" + service + '\'' +
                ", extrenalServiceId='" + extrenalServiceId + '\'' +
                '}';
    }
}
