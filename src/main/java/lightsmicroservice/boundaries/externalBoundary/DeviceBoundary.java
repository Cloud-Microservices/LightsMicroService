package lightsmicroservice.boundaries.externalBoundary;

import lightsmicroservice.boundaries.StatusBoundary;

import java.util.Date;
import java.util.Map;

public class DeviceBoundary {
    private String id;
    private String type;
    private String subType;
    private Date registrationTimestamp;
    private Date lastUpdateTimestamp;
    private String location;
    private Double manufacturerPowerInWatts;
    private StatusBoundary status;
    private Map<String, Object> additionalAttributes;

    public DeviceBoundary() {}

    public DeviceBoundary(String id, String type, String subType, Date registrationTimestamp, Date lastUpdateTimestamp, String location, Double manufacturerPowerInWatts, StatusBoundary status, Map<String, Object> additionalAttributes) {
        this.id = id;
        this.type = type;
        this.subType = subType;
        this.registrationTimestamp = registrationTimestamp;
        this.lastUpdateTimestamp = lastUpdateTimestamp;
        this.location = location;
        this.manufacturerPowerInWatts = manufacturerPowerInWatts;
        this.status = status;
        this.additionalAttributes = additionalAttributes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
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

    public StatusBoundary getStatus() {
        return status;
    }

    public void setStatus(StatusBoundary status) {
        this.status = status;
    }

    public Map<String, Object> getAdditionalAttributes() {
        return additionalAttributes;
    }

    public void setAdditionalAttributes(Map<String, Object> additionalAttributes) {
        this.additionalAttributes = additionalAttributes;
    }

    public boolean isLightDevice() {
        return "Light".equals(this.type);
    }

    @Override
    public String toString() {
        return "DeviceBoundary{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", subType='" + subType + '\'' +
                ", registrationTimestamp=" + registrationTimestamp +
                ", lastUpdateTimestamp=" + lastUpdateTimestamp +
                ", location='" + location + '\'' +
                ", manufacturerPowerInWatts=" + manufacturerPowerInWatts +
                ", status=" + status +
                ", additionalAttributes=" + additionalAttributes +
                '}';
    }
}
