package lightsMicroService.boundaries;

import lightsMicroService.data.StatusEntity;

import java.util.Arrays;

public class StatusBoundary {
    private int brightness;
    private int[] colorRGB;
    private Boolean isOn;

    public StatusBoundary() {
    }
    public StatusBoundary(StatusEntity entity) {
        this.setBrightness(entity.getBrightness());
        this.setIsOn(entity.getIsOn());
        this.setColorRGB(entity.getColorRGB());

    }
    public StatusEntity toEntity() {
        StatusEntity rv = new StatusEntity();
        rv.setBrightness(this.getBrightness());
        rv.setIsOn(this.getIsOn());
        rv.setColorRGB(this.getColorRGB());
        return rv;

    }

    public StatusBoundary(int brightness, int[] colorRGB, boolean isOn) {
        this.brightness = brightness;
        this.colorRGB = colorRGB;
        this.isOn = isOn;
    }

    public int[] getColorRGB() {
        return colorRGB;
    }

    public void setColorRGB(int[] colorRGB) {
        // Check if the array is valid
        if (colorRGB != null && colorRGB.length == 3 &&
                Arrays.stream(colorRGB).allMatch(value -> value >= 0 && value <= 255)) {
            this.colorRGB = colorRGB;
        } else {
            // Default to white color if validation fails
            this.colorRGB = new int[]{255, 255, 255};
        }
    }

    public int getBrightness() {
        return brightness;
    }

    public void setBrightness(int brightness) {
        this.brightness = brightness;
    }

    public boolean getIsOn() {
        return isOn;
    }

    public void setIsOn(boolean on) {
        isOn = on;
    }

    @Override
    public String toString() {
        return "StatusBoundary{" +
                "brightness=" + brightness +
                ", colorRGB=" + Arrays.toString(colorRGB) +
                ", isOn=" + isOn +
                '}';
    }
}
