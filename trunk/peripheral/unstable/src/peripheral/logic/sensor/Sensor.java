package peripheral.logic.sensor;

import java.util.ArrayList;
import java.util.Observable;


public class Sensor extends Observable {

    private long pid;
    private String name;
    private SensorServer server;
    private float samplerate;
    private ArrayList<SensorChannel> channels;
    private String startmark;
    
    private CSVCheckout csv;
    private CSVSamplerateEstimator estimator;

    public Sensor (long pid, String name, SensorServer server, float samplerate) {
    	
    	this.pid = pid;
    	this.name = name;
    	this.server = server;
    	this.samplerate = samplerate;
    	this.channels = new ArrayList<SensorChannel>();
    	
    	this.startmark = "0";
    	this.csv = new CSVCheckout(this);
    	this.estimator = new CSVSamplerateEstimator(this);
    
    }
 
    protected void estimateSamplerateFromCSV(){
    	estimator.start();
    }
    
    public void startCheckout(){
    	csv.start();
    }
    
    public void stopCheckout(){
    	csv.setActive(false);
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
        setChanged();
        notifyObservers(new SensorSamplingRateChanged(samplerate));
    }

    public String getStartmark(){
    	return startmark;
    }
    
    public void setStartmark(String lastmark){
    	this.startmark = lastmark;
    }
    
}

