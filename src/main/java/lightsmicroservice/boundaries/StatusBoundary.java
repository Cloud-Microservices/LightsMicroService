package lightsmicroservice.boundaries;

import lightsmicroservice.data.StatusEntity;

import java.util.Arrays;

public class StatusBoundary {
    private Integer brightness;
    private int[] colorRGB;
    private Boolean isOn;
    private Double currentPowerInWatts;

    public StatusBoundary() {
    }
    public StatusBoundary(StatusEntity entity) {
        this.setBrightness(entity.getBrightness());
        this.setIsOn(entity.getIsOn());
        this.setColorRGB(entity.getColorRGB());
        this.setCurrentPowerInWatts(entity.getCurrentPowerInWatts());
    }
    public StatusEntity toEntity() {
        StatusEntity rv = new StatusEntity();
        rv.setBrightness(this.getBrightness());
        rv.setIsOn(this.getIsOn());
        rv.setColorRGB(this.getColorRGB());
        rv.setCurrentPowerInWatts(this.getCurrentPowerInWatts());
        return rv;

    }

    public StatusBoundary(int brightness, int[] colorRGB, boolean isOn, Double currentPowerInWatts) {
        this.brightness = brightness;
        this.colorRGB = colorRGB;
        this.isOn = isOn;
        this.currentPowerInWatts = currentPowerInWatts;
    }

    public int[] getColorRGB() {
        return colorRGB;
    }

    public void setColorRGB(int[] colorRGB) {
        this.colorRGB = colorRGB;
    }

    public Integer getBrightness() {
        return brightness;
    }

    public void setBrightness(int brightness) {
        this.brightness = brightness;
    }

    public Boolean getIsOn() {
        return isOn;
    }

    public void setIsOn(boolean on) {
        isOn = on;
    }

    public Double getCurrentPowerInWatts() {
        return currentPowerInWatts;
    }

    public void setCurrentPowerInWatts(Double currentPowerInWatts) {
        this.currentPowerInWatts = currentPowerInWatts;
    }

    @Override
    public String toString() {
        return "StatusBoundary{" +
                "brightness=" + brightness +
                ", colorRGB=" + Arrays.toString(colorRGB) +
                ", isOn=" + isOn +
                ", currentPowerInWatts=" + currentPowerInWatts +
                '}';
    }
}
