package rsocketmessagingservice.boundaries;

public class ExtrenalReferenceBoundary {
    private String service;
    private String extrenalServiceId;

    public ExtrenalReferenceBoundary(String service, String extrenalServiceId) {
        this.service = service;
        this.extrenalServiceId = extrenalServiceId;
    }

    public ExtrenalReferenceBoundary() {
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
        return "ExtrenalReferenceBoundary{" +
                "service='" + service + '\'' +
                ", extrenalServiceId='" + extrenalServiceId + '\'' +
                '}';
    }
}
