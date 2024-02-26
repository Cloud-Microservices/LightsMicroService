package lightsMicroService.boundaries;

import java.util.Arrays;

public class LightStatusBoundary {

    private String id;

    private StatusBoundary status;

    public LightStatusBoundary() {

    }

    public LightStatusBoundary(String id, StatusBoundary status) {
        this.id = id;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public StatusBoundary getStatus() {
        return status;
    }

    public void setStatus(StatusBoundary status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "LightStatusBoundary{" +
                "id='" + id + '\'' +
                ", status=" + status +
                '}';
    }
}
