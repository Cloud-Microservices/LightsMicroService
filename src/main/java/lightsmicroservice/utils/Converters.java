package lightsmicroservice.utils;

import lightsmicroservice.boundaries.LightBoundary;
import lightsmicroservice.boundaries.StatusBoundary;
import lightsmicroservice.boundaries.externalBoundary.DeviceBoundary;
import lightsmicroservice.data.LightEntity;
import lightsmicroservice.data.StatusEntity;
import org.springframework.stereotype.Component;

@Component
public class Converters {

    public DeviceBoundary toDeviceBoundary(LightEntity lightEntity) {
        DeviceBoundary deviceBoundary = setDeviceBoundaryFields(new LightBoundary(lightEntity));

        deviceBoundary.setStatus(new StatusBoundary(lightEntity.getStatus()));
        return deviceBoundary;
    }

    public DeviceBoundary toDeviceBoundary(LightBoundary lightBoundary) {
        DeviceBoundary deviceBoundary = setDeviceBoundaryFields(lightBoundary);

        StatusBoundary statusBoundary = new StatusBoundary(new StatusEntity().setDefaultStatus());
        deviceBoundary.setStatus(statusBoundary);
        return deviceBoundary;
    }

    public DeviceBoundary toDeviceBoundary(LightBoundary lightBoundary, StatusBoundary statusBoundary) {

        DeviceBoundary deviceBoundary = setDeviceBoundaryFields(lightBoundary);
        deviceBoundary.setStatus(statusBoundary);
        return deviceBoundary;
    }

    private DeviceBoundary setDeviceBoundaryFields(LightBoundary lightBoundary) {
        DeviceBoundary deviceBoundary = new DeviceBoundary();
        deviceBoundary.setId(lightBoundary.getId());
        deviceBoundary.setType("Light");
        deviceBoundary.setSubType(lightBoundary.getLightType());
        deviceBoundary.setRegistrationTimestamp(lightBoundary.getRegistrationTimestamp());
        deviceBoundary.setLastUpdateTimestamp(lightBoundary.getLastUpdateTimestamp());
        deviceBoundary.setLocation(lightBoundary.getLocation());
        deviceBoundary.setManufacturerPowerInWatts(lightBoundary.getManufacturerPowerInWatts());
        deviceBoundary.setAddionalAttributes(null);

        return deviceBoundary;
    }




}
