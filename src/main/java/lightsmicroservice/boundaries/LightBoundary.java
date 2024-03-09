package lightsmicroservice.boundaries;

import lightsmicroservice.data.LightEntity;
import lightsmicroservice.data.StatusEntity;

import java.util.Date;

public class LightBoundary {

    private String id;
    private String lightType;
    private String alias;
    private Date registrationTimestamp;
    private Date lastUpdateTimestamp;
    private String location;
    private Double manufacturerPowerInWatts;

    public LightBoundary(LightEntity entity) {
        this.setId(entity.getId());
        this.setRegistrationTimestamp(entity.getRegistrationTimestamp());
        this.setLastUpdateTimestamp(entity.getLastUpdateTimestamp());
        this.setAlias(entity.getAlias());
        this.setLocation(entity.getLocation());
        this.setLightType(entity.getLightType());
        this.setManufacturerPowerInWatts(entity.getManufacturerPowerInWatts());
    }

    public LightEntity toEntity() {
        LightEntity rv = new LightEntity();
        rv.setId(this.getId());
        rv.setAlias(this.getAlias());
        rv.setLightType(this.getLightType());
        rv.setLocation(this.getLocation());
        rv.setRegistrationTimestamp(this.getRegistrationTimestamp());
        rv.setLastUpdateTimestamp(this.getLastUpdateTimestamp());
        rv.setStatus(new StatusEntity().setDefaultStatus());
        rv.setManufacturerPowerInWatts(this.getManufacturerPowerInWatts());
        return rv;
    }

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

    public Double getManufacturerPowerInWatts() {
        return manufacturerPowerInWatts;
    }

    public void setManufacturerPowerInWatts(Double manufacturerPowerInWatts) {
        this.manufacturerPowerInWatts = manufacturerPowerInWatts;
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
                ", manufacturerPowerInWatts=" + manufacturerPowerInWatts +
                '}';
    }
}
