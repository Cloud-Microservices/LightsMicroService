package lightsmicroservice.data;

import java.util.Arrays;

public class StatusEntity {
    private Integer brightness;
    private int[] colorRGB;
    private Boolean isOn;

    public StatusEntity(Integer brightness, int[] colorRGB, Boolean isOn) {
        this.brightness = brightness;
        this.colorRGB = colorRGB;
        this.isOn = isOn;
    }

    public StatusEntity() {
    }

    public StatusEntity setDefaultStatus() {
        this.brightness = 100;
        this.colorRGB = new int[]{255,255,255};
        this.isOn = false;
        return this;
    }

    public Integer getBrightness() {
        return brightness;
    }

    public StatusEntity setBrightness(Integer brightness) {
        this.brightness = brightness;
        return this;
    }

    public int[] getColorRGB() {
        return colorRGB;
    }

    public StatusEntity setColorRGB(int[] colorRGB) {
        this.colorRGB = colorRGB;
        return this;
    }

    public Boolean getIsOn() {
        return isOn;
    }

    public StatusEntity setIsOn(Boolean on) {
        isOn = on;
        return this;
    }

    @Override
    public String toString() {
        return "StatusEntity{" +
                "brightness=" + brightness +
                ", colorRGB=" + Arrays.toString(colorRGB) +
                ", isOn=" + isOn +
                '}';
    }
}
