package lightsmicroservice.data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "lights")
public class LightEntity {
    @Id
    private String id;

    private String lightType;

    private String alias;

    private Date registrationTimestamp;

    private Date lastUpdateTimestamp;

    private String location;

    private StatusEntity status;

    public LightEntity() {
    }

    public LightEntity(String id, String lightType, String alias, Date registrationTimestamp, Date lastUpdateTimestamp, String location, StatusEntity status) {
        this.id = id;
        this.lightType = lightType;
        this.alias = alias;
        this.registrationTimestamp = registrationTimestamp;
        this.lastUpdateTimestamp = lastUpdateTimestamp;
        this.location = location;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLightType() {
        return lightType;
    }

    public void setLightType(String lightType) {
        this.lightType = lightType;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public Date getRegistrationTimestamp() {
        return registrationTimestamp;
    }

    public void setRegistrationTimestamp(Date registrationTimestamp) {
        this.registrationTimestamp = registrationTimestamp;
    }

    public Date getLastUpdateTimestamp() {
        return lastUpdateTimestamp;
    }

    public void setLastUpdateTimestamp(Date lastUpdateTimestamp) {
        this.lastUpdateTimestamp = lastUpdateTimestamp;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public StatusEntity getStatus() {
        return status;
    }

    public void setStatus(StatusEntity status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "LightEntity{" +
                "id='" + id + '\'' +
                ", lightType='" + lightType + '\'' +
                ", alias='" + alias + '\'' +
                ", registrationTimestamp=" + registrationTimestamp +
                ", lastUpdateTimestamp=" + lastUpdateTimestamp +
                ", location='" + location + '\'' +
                ", status=" + status +
                '}';
    }

}
