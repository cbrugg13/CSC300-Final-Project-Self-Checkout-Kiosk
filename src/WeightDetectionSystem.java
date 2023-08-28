public class WeightDetectionSystem {
    private float minWeight;
    private float maxWeight;
    private boolean isCalibrated;

    public WeightDetectionSystem(float minWeight, float maxWeight, boolean isCalibrated) {
        this.minWeight = minWeight;
        this.maxWeight = maxWeight;
        this.isCalibrated = isCalibrated;
    }

    public float getMinWeight() {
        return minWeight;
    }

    public float getMaxWeight() {
        return maxWeight;
    }

    public boolean isCalibrated() {
        return isCalibrated;
    }
}
