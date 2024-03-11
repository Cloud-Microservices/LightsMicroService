package lightsmicroservice.boundaries;

import lightsmicroservice.data.StatusEntity;

public class EnergyStatusBoundary extends StatusBoundary{
    private Double currentPowerInWatts;

    public EnergyStatusBoundary() {
    }

    public EnergyStatusBoundary(StatusEntity entity) {
        super(entity);
        this.currentPowerInWatts = entity.getCurrentPowerInWatts();
    }

    public EnergyStatusBoundary(Integer brightness, int[] colorRGB, Boolean isOn, Double currentPowerInWatts) {
        super(brightness, colorRGB, isOn);
        this.currentPowerInWatts = currentPowerInWatts;
    }

    public Double getCurrentPowerInWatts() {
        return currentPowerInWatts;
    }

    public void setCurrentPowerInWatts(Double currentPowerInWatts) {
        this.currentPowerInWatts = currentPowerInWatts;
    }

    @Override
    public String toString() {
        return "EnergyStatusBoundary{" +
                "currentPowerInWatts=" + currentPowerInWatts +
                '}';
    }
}
