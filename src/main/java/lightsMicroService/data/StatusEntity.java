package lightsMicroService.data;

public class StatusEntity {

    private int brightness;
    private int[] colorRGB;
    private boolean isOn;

    public StatusEntity(int brightness, int[] colorRGB, boolean isOn) {
        this.brightness = brightness;
        this.colorRGB = colorRGB;
        this.isOn = isOn;
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

    public boolean isOn() {
        return isOn;
    }

    public void setOn(boolean on) {
        isOn = on;
    }
}
