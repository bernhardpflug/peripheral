package peripheral.logic.sensor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import peripheral.logic.Logging;
import peripheral.logic.datatype.Primitives;
import peripheral.logic.sensor.exception.SensorChannelException;
import peripheral.logic.sensor.exception.SensorServerAddressException;

public class Sensor extends Observable implements Serializable {

	// Basic sensor attributes
    private long pid;
    private String name;
    
    // Object references
    private SensorServer server;
    private ArrayList<SensorChannel> channels;
    private transient CSVCheckout csv;

    // Variables for data retrieval
    private float samplerate;
    private float pollingrate;
    private String startmark;
    
    // Variables for dynamic sample rate adoption
    private int measQueue_current;
    private int srupdatecnt;									// Counter for number of polls since samplerate has been modified for the last time
    
    // Statics, mostly factors which need to be adjusted after testing with rule engine and visualization components
    private static final int latency = 5;						// Represents poll latency after which samplerate may be increased/decreased if queue size is out of bounds
    private static final float init_samplerate = 1.0f;
    private static final int sr_polling_ratio = 10;				// Factor of how many samples should occur with one poll
    private static final float sr_adoption_factor = 0.3f; 		// Factor which is added/subtracted from current samplerate in percent of current samplerate
    private static final int measQueue_max = 20;
    private static final int measQueue_min = 5;
    
    public Sensor (long pid, String name, SensorServer server) {
    	
    	// Reference objects and attributes
    	this.pid = pid;
    	this.name = name;
    	this.server = server;
    	this.channels = new ArrayList<SensorChannel>();
    	
    	// Set sensors startmark to 0
    	this.startmark = "0";
    	
    	// Create CSV Checkout instance
    	this.csv = new CSVCheckout(this);
    	
    	// Set init samplerate
    	samplerate = init_samplerate;
    	
    	// Set pollingrate to ten times of the samplerate
    	pollingrate = samplerate*sr_polling_ratio;
    	
    	// Initialize variables for dynamic sr adoption
    	srupdatecnt = 0;
    
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
    
    // Privates
    private void checkQueueSizeAndSamplerate(){
    	
    	srupdatecnt++;
    	
    	// Check if new value is located within predefined upper and lower bounds
		if(measQueue_current < measQueue_min && srupdatecnt >= latency){
			
			// Increase sleeptime about sr_adoption_factor
			samplerate += (samplerate*sr_adoption_factor);
			srupdatecnt = 0;
			
			Logging.getLogger().fine("Decreased samplerate to: " + samplerate);
			
			// Update pollingrate if smaller or equal to samplerate
			 if(pollingrate<=samplerate){
		        	pollingrate = samplerate*sr_polling_ratio;
		        	Logging.getLogger().fine("Adjusted pollingrate to: " + samplerate);
		        }
			
		}else if (measQueue_current > measQueue_max && srupdatecnt >= latency){
		
			// Decrease sleeptime about sr_adoption_factor
			samplerate -= (samplerate*sr_adoption_factor);
			srupdatecnt = 0;
			
			Logging.getLogger().fine("Increased samplerate to: " + samplerate);
			
			// Update pollingrate if smaller or equal to samplerate
			 if(pollingrate<=samplerate){
		        	pollingrate = samplerate*sr_polling_ratio;
		        	Logging.getLogger().fine("Adjusted pollingrate to: " + samplerate);
		        }
		}
		
    }
    
    // GETTERS AND SETTERS
    public ArrayList<SensorChannel> getSensorChannels () {
        return channels;
    }

    /**
     *
     * @param allowedType: type, which a SensorChannel's type have to be assignable, to be returned in the list
     * @return list, which only holds SensorChannels, whose types are assignable to the passed allowed type
     */
    public List<SensorChannel> getSensorChannels (Class allowedType){
        List<SensorChannel> allowedChannels = new ArrayList<SensorChannel>();

        for (SensorChannel channel : channels){
            if (allowedType.isAssignableFrom(channel.getDatatype())){
                allowedChannels.add(channel);
            }
            //if allowedType is of type number, do special handling:
            //int should be assignable to long, float, double,
            //long should be assignable to float, double, etc.
            else if (Number.class.isAssignableFrom(allowedType)){
                if (Primitives.isAssignable(allowedType, channel.getDatatype())){
                    allowedChannels.add(channel);
                }
            }
        }

        return allowedChannels;
    }

    @Override
    public boolean equals(Object obj) {

        if (obj instanceof Sensor) {
            Sensor s = (Sensor)obj;

            if (this.pid == s.getPid()) {
                return true;
            }
            else {
                return false;
            }
        }
        else {
            return super.equals(obj);
        }
    }

    @Override
    public String toString() {
        return this.name;
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

	public int getMeasQueue_max() {
		return measQueue_max;
	}

	public int getMeasQueue_min() {
		return measQueue_min;
	}

	public int getMeasQueueCurrentSize() {
		return measQueue_current;
	}

	public void setMeasQueueCurrentSize(int measQueueCurrentSize) {
		this.measQueue_current = measQueueCurrentSize;
		checkQueueSizeAndSamplerate();
	}

	public float getPollingrate() {
		return pollingrate;
	}

	public void setPollingrate(float pollingrate) {
		this.pollingrate = pollingrate;
	}

	public static int getSr_polling_ratio() {
		return sr_polling_ratio;
	}
    
}

