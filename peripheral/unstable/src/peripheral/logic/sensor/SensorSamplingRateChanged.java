package peripheral.logic.sensor;


public class SensorSamplingRateChanged implements SensorEvent {

    private float samplerate;

    public SensorSamplingRateChanged (float samplingrate) {
    	this.samplerate = samplingrate;
    }
    
    // GETTERS AND SETTERS
    public float getSamplerate () {
        return samplerate;
    }

}

