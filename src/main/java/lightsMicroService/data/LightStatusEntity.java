package lightsMicroService.data;

import lightsMicroService.boundaries.StatusBoundary;

public class LightStatusEntity {

    private String id;

    private StatusEntity status;

    public LightStatusEntity() {
    }

    public LightStatusEntity(String id, StatusEntity status) {
        this.id = id;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public StatusEntity getStatus() {
        return status;
    }

    public void setStatus(StatusEntity status) {
        this.status = status;
    }
}
