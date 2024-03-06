package lightsmicroservice.boundaries.externalBoundary;

import lightsmicroservice.boundaries.StatusBoundary;

import java.util.Date;

public class DeviceBoundary {
    private String id;
    private static final String type ="Light";
    private String subType;
    private Date registrationTimestamp;
    private Date lastUpdateTimestamp;
    private String location;
    private int manufacturerPowerInWatts;
    private StatusBoundary status;
    private int currentPowerInWatts;

    public DeviceBoundary() {}
    public DeviceBoundary(String id, String subType, Date registrationTimestamp, Date lastUpdateTimestamp, String location, int manufacturerPowerInWatts, StatusBoundary status, int currentPowerInWatts) {
        this.id = id;
        this.subType = subType;
        this.registrationTimestamp = registrationTimestamp;
        this.lastUpdateTimestamp = lastUpdateTimestamp;
        this.location = location;
        this.manufacturerPowerInWatts = manufacturerPowerInWatts;
        this.status = status;
        this.currentPowerInWatts = currentPowerInWatts;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public int getManufacturerPowerInWatts() {
        return manufacturerPowerInWatts;
    }

    public void setManufacturerPowerInWatts(int manufacturerPowerInWatts) {
        this.manufacturerPowerInWatts = manufacturerPowerInWatts;
    }

    public StatusBoundary getStatus() {
        return status;
    }

    public void setStatus(StatusBoundary status) {
        this.status = status;
    }

    public int getCurrentPowerInWatts() {
        return currentPowerInWatts;
    }

    public void setCurrentPowerInWatts(int currentPowerInWatts) {
        this.currentPowerInWatts = currentPowerInWatts;
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
                ", currentPowerInWatts=" + currentPowerInWatts +
                '}';
    }




    //        "messageId": "123",
//            "publishedTimestamp": "2024-02-11T15:22:13.730+0000",
//            "messageType": "deviceNotification",
//            "summary": "device {deviceId} has been turned on/off",
//            "externalReferences": [
//        {
//            "service": "string",
//                "externalServiceId": "string"
//        }
//  ],
//        "messageDetails": {
    //************************LIGHT BOUNDARY******************************
//        "device": {
//                    "id": "device1",
    //&&&&&&&&&&&&&&&&&&&&&&&&&& CONST &&&&&&&&&&&&&&&&&&&&&&&&&&&&
//                    "type": "Light",
    //&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&

    // **************subType ======LightType*************
//                    "subType": "LED",
//                    "registrationTimestamp": "2024-02-11T15:22:13.730+0000",
//                    "lastUpdateTimestamp": "2024-02-13T17:10:13.545+0000",
//                    "location": "kitchen",
    //&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
//                    "manufacturerPowerInWatts": 50,
    //&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&


    //************************STATUS BOUNDARY******************************
//                    "status": {
//                        "isOn": true,
//                        "brightness": 75,
//                        "colorRGB": [
//                        255,
//                        255,
//                        255
//                       ],
    //&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
//                "currentPowerInWatts": 52
    //&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&

//            }
//        }
//    }
//    }
}
