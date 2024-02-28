package lightsMicroService.data;

import lightsMicroService.boundaries.StatusBoundary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "lightsStatus")

public class LightStatusEntity {

    @Id private String id;

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
