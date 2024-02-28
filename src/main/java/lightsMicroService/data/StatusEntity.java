package lightsMicroService.data;

import java.util.Arrays;

public class StatusEntity {

    private int brightness;
    private int[] colorRGB;
    private boolean isOn;

    public StatusEntity(int brightness, int[] colorRGB, boolean isOn) {
        this.brightness = brightness;
        this.colorRGB = colorRGB;
        this.isOn = isOn;
    }

    public StatusEntity() {
    }

    public int getBrightness() {
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

    public boolean getIsOn() {
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
