package lightsMicroService.data;

import lightsMicroService.boundaries.StatusBoundary;

public class LightStatusEntity {

    private String id;

    private StatusBoundary status;

    public LightStatusEntity() {
    }

    public LightStatusEntity(String id, StatusBoundary status) {
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
}
