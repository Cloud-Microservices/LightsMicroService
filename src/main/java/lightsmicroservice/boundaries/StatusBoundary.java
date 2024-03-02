package lightsmicroservice.boundaries;

import lightsmicroservice.data.StatusEntity;

import java.util.Arrays;

public class StatusBoundary {
    private Integer brightness;
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

    @Override
    public String toString() {
        return "StatusBoundary{" +
                "brightness=" + brightness +
                ", colorRGB=" + Arrays.toString(colorRGB) +
                ", isOn=" + isOn +
                '}';
    }
}
