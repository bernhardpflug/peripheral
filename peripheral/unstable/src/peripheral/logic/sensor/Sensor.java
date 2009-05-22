package peripheral.logic.sensor;

import java.util.ArrayList;
import java.util.Observable;


public class Sensor extends Observable {

    private long pid;
    private String name;
    private SensorServer server;
    private float samplerate;
    private ArrayList<SensorChannel> channels;
    
    private CSVCheckout csv;

    public Sensor (long pid, String name, SensorServer server, float samplerate) {
    	this.pid = pid;
    	this.name = name;
    	this.server = server;
    	this.samplerate = samplerate;
    	this.channels = new ArrayList<SensorChannel>();
    	
    	this.csv = new CSVCheckout(this);
    	
    	// Dummy Method
//    	observeSampleRate();
//    	getObservedSampleRate();
    }
    
    public float observeSampleRate(){
    	//TODO call function of CSVCheckout to observe samplerate of meas
    	return 1.0f;
    }
    
//    protected void getObservedSampleRate(){
//    	
//    	samplerate = 2.0f;
//    	setChanged();
//    	notifyObservers(new SensorSamplingStarted(samplerate));
//    
//    }

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
        setChanged();
        notifyObservers(new SensorSamplingRateChanged(samplerate));
    }

}

