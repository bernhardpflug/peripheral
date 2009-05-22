package peripheral.logic.sensor;


public class SensorSamplingStarted implements SensorEvent {

	private float samplerate;
	
    public SensorSamplingStarted (float samplingrate) {
    	this.samplerate = samplingrate;
    }
    
    // GETTERS AND SETTERS
	public float getSamplerate() {
		return samplerate;
	}

}

