package lightsMicroService.boundaries;

public class LocationStatusBoundary {

    private String location;

    private StatusBoundary status;

    public LocationStatusBoundary() {
    }

    public LocationStatusBoundary(String location, StatusBoundary status) {
        this.location = location;
        this.status = status;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public StatusBoundary getStatus() {
        return status;
    }

    public void setStatus(StatusBoundary status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "LocationStatusBoundary{" +
                "location='" + location + '\'' +
                ", status=" + status +
                '}';
    }
}
