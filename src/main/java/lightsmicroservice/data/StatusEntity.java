package lightsmicroservice.data;

import java.util.Arrays;

public class StatusEntity {

    private Integer brightness;
    private int[] colorRGB;
    private Boolean isOn;

    public StatusEntity(int brightness, int[] colorRGB, boolean isOn) {
        this.brightness = brightness;
        this.colorRGB = colorRGB;
        this.isOn = isOn;
    }

    public StatusEntity() {
    }

    public Integer getBrightness() {
        return brightness;
    }

    public void setBrightness(int brightness) {
        this.brightness = brightness;
    }

    public int[] getColorRGB() {
        return colorRGB;
    }

    public void setColorRGB(int[] colorRGB) {
        this.colorRGB = colorRGB;
    }

    public Boolean getIsOn() {
        return isOn;
    }

    public void setIsOn(boolean on) {
        isOn = on;
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
