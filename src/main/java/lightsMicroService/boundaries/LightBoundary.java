package lightsMicroService.boundaries;

import java.util.Date;

public class LightBoundary {

    private String id;

    private String lightType;

    private String alias;

    private Date registrationTimestamp;

    private Date lastUpdateTimestamp;

    private String location;

    public LightBoundary() {
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

    @Override
    public String toString() {
        return "LightBoundary{" +
                "id='" + id + '\'' +
                ", lightType='" + lightType + '\'' +
                ", alias='" + alias + '\'' +
                ", registrationTimestamp=" + registrationTimestamp +
                ", lastUpdateTimestamp=" + lastUpdateTimestamp +
                ", location='" + location + '\'' +
                '}';
    }
}
