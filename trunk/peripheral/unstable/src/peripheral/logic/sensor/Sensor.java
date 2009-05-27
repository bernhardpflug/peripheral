package peripheral.logic.sensor;

import java.util.ArrayList;
import java.util.Observable;

import peripheral.logic.sensor.exception.SensorChannelException;
import peripheral.logic.sensor.exception.SensorServerAddressException;


public class Sensor extends Observable {

	// Basic sensor attributes
    private long pid;
    private String name;
    
    // Object references
    private SensorServer server;
    private ArrayList<SensorChannel> channels;
    private CSVCheckout csv;

    // Variables for data retrieval
    private float samplerate = 1.0f;
    private float pollingrate = 1.0f;
    private String startmark;
    private int measQueue_max;
    private int measQueue_min;

    public Sensor (long pid, String name, SensorServer server) {
    	
    	this.pid = pid;
    	this.name = name;
    	this.server = server;
    	this.channels = new ArrayList<SensorChannel>();
    	
    	this.startmark = "0";
    	this.csv = new CSVCheckout(this);
    
    }

	public void startCheckout(){
    	
    		System.err.println("Samplerate: " + samplerate);
//			this.startmark = calculateStartmark();
			csv.start();
			updateSamplerate();
    }
    
    public void stopCheckout(){
    	csv.interrupt();
    }
    
    protected void samplingStarted(){
    	setChanged();
    	notifyObservers(new SensorSamplingStarted());
    }
    
    public String calculateStartmark() throws SensorServerAddressException, SensorChannelException{
    	CSVStartmarkEstimator estimator = new CSVStartmarkEstimator(this);
    	return estimator.getLatestmark();
    }
    
    public void updateSamplerate(){
    	CSVSamplerateEstimator estimator = new CSVSamplerateEstimator(this);
    	estimator.start();
    }
    
    public float calulcateHttpDelayFactor(){
    	return 0.1f;
    }
    
    // GETTERS AND SETTERS
    public ArrayList<SensorChannel> getSensorChannels () {
        return channels;
    }

    public String getName () {
        return name;
    }

    public long getPid () {
        return pid;
    }

    public SensorServer getServer () {
        return server;
    }

    public float getSamplerate () {
        return samplerate;
    }

    public void setSamplerate (float samplerate) {
        this.samplerate = samplerate;
    }

    public String getStartmark(){
    	return startmark;
    }
    
    public void setStartmark(String lastmark){
    	this.startmark = lastmark;
    }
    
    public float getPollingrate() {
		return pollingrate;
	}

	public void setPollingrate(float pollingrate) {
		this.pollingrate = pollingrate;
	}

	public int getMeasQueue_max() {
		return measQueue_max;
	}

	public void setMeasQueue_max(int measQueue_max) {
		this.measQueue_max = measQueue_max;
	}

	public int getMeasQueue_min() {
		return measQueue_min;
	}

	public void setMeasQueue_min(int measQueue_min) {
		this.measQueue_min = measQueue_min;
	}
    
}

