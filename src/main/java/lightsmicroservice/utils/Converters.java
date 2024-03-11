package lightsmicroservice.utils;

import lightsmicroservice.boundaries.EnergyStatusBoundary;
import lightsmicroservice.boundaries.LightBoundary;
import lightsmicroservice.boundaries.externalBoundary.DeviceBoundary;
import lightsmicroservice.data.LightEntity;
import lightsmicroservice.data.StatusEntity;
import org.springframework.stereotype.Component;

@Component
public class Converters {

    public DeviceBoundary toDeviceBoundary(LightEntity lightEntity) {
        DeviceBoundary deviceBoundary = setDeviceBoundaryFields(new LightBoundary(lightEntity));

        deviceBoundary.setStatus(new EnergyStatusBoundary(lightEntity.getStatus()));
        System.err.println(deviceBoundary);
        return deviceBoundary;
    }

    public DeviceBoundary toDeviceBoundary(LightBoundary lightBoundary) {
        DeviceBoundary deviceBoundary = setDeviceBoundaryFields(lightBoundary);

        EnergyStatusBoundary statusBoundary = new EnergyStatusBoundary(new StatusEntity().setDefaultStatus());
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
        deviceBoundary.setAdditionalAttributes(null);

        return deviceBoundary;
    }




}
