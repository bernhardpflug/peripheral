package peripheral.logic.sensor;


public class SensorSamplingRateChanged implements SensorEvent {

    private int samplingRate;

    private int Unnamed;

    public SensorSamplingRateChanged () {
    }

    public int getSamplingRate () {
        return samplingRate;
    }

    public void setSamplingRate (int val) {
        this.samplingRate = val;
    }

}

