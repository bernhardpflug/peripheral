package peripheral.logic.sensor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import peripheral.logic.Logging;
import peripheral.logic.datatype.Primitives;
import peripheral.logic.sensor.exception.SensorChannelException;
import peripheral.logic.sensor.exception.SensorServerAddressException;

public class Sensor extends Observable implements Serializable {

    // Object references
    private SensorServer server;
    private ArrayList<SensorChannel> channels;
    private transient CSVCheckout checkout;
    
    // Sensor values
    private long pid;
    private String name;
    
    // Mark of latest measurement for this sensor
    private String mark;

    // Variables for data retrieval
    private float samplerate;
    private float pollingrate;
    private static final float init_samplerate = 1.0f;
    private static final float init_pollingrate = 1.0f;
    
    // Queue values
    private static final int queuesize_max = 10;
    private static final int queuesize_min = 1;
    private int queuesize_current;
    private int queuesize_previous;
    private int diff_current;
    private int diff_previous;
    
    // Factor for adjusting sample rate
    // IMPORTANT: Set > 1.0f
    private static final float factor = 1.3f;
    
    // To check if measurements have been added last checkout-iteration
    private boolean queue_updated;
    
    // To check if Visualization knows about sampling, set to true when samplingStarted is called and to false if samplingStoped is called
    private boolean sampling_started;

    public Sensor(long pid, String name, SensorServer server) {

        // Reference objects and attributes
        this.pid = pid;
        this.name = name;
        this.server = server;
        channels = new ArrayList<SensorChannel>();

        // Initialize values
        mark = "0";
        queuesize_current = 0;
        queuesize_previous = 0;
        diff_current = 0;
        diff_previous = 0;
        samplerate = init_samplerate;
        pollingrate = init_pollingrate;
        queue_updated = false;
        sampling_started = false;

        // Create CSV Checkout instance
        checkout = new CSVCheckout(this);
        
    }

    private void readObject(ObjectInputStream in) throws ClassNotFoundException, IOException {
        in.defaultReadObject();
        checkout = new CSVCheckout(this);
    }

    public void startCheckout() {
        checkout.start();
        updateSamplerate();
    }

    public void stopCheckout() {
        checkout.interrupt();
    }

    protected void samplingStarted() {
        setChanged();
        notifyObservers(new SensorSamplingStarted());
        sampling_started = true;
    }

    public String calculateStartmark() throws SensorServerAddressException, SensorChannelException {
        CSVStartmarkEstimator estimator = new CSVStartmarkEstimator(this);
        return estimator.getLatestmark();
    }

    public void updateSamplerate() {
        CSVSamplerateEstimator estimator = new CSVSamplerateEstimator(this);
        estimator.start();
    }

    private void checkQueueSizeAndSamplerate() {

        if (queuesize_current > queuesize_max) {
        	
        	// Check if size of queue is still increasing
        	if(queuesize_current > queuesize_previous){
        		
        		diff_current = queuesize_current - queuesize_previous;
        		
        		if(diff_current >= diff_previous){
                    // Faster queue polling through decreasing sleeptime
                    if(samplerate > 0.01f){
                    	samplerate = samplerate/factor;
                        Logging.getLogger().finest("Adjusted samplerate of sensor " + this.getName() + "to: " + samplerate);
                    }else if(samplerate<0.01f){
                    	Logging.getLogger().finest("Samplerate of sensor " + this.getName() + " reached minimum of 0.01");
                    }
                    
                    // Set values for next iteration
            		diff_previous = diff_current;
            		
        		}else{
        			// Queue size is decreasing towards its boundaries
        			diff_current = 0;
        			diff_previous = 0;
        		}
        	}

            // Update pollingrate if smaller or equal to samplerate
            if (pollingrate <= samplerate) {
                pollingrate = samplerate;
                Logging.getLogger().finest("Adjusted pollingrate of sensor " + this.getName() + "to: " + pollingrate);
            }

        } else if (queuesize_current < queuesize_max  && queue_updated) {

        	if(queuesize_current == 0){
        		
        		diff_previous = 0;
        		diff_current = 0;
        		
        		// Slower queue polling through increasing sleeptime
                samplerate = samplerate*factor;
                Logging.getLogger().finest("Adjusted samplerate of sensor " + this.getName() + "to: " + samplerate);
                
        	}else if(queuesize_current > 0){
        		
        		diff_current = queuesize_current - queuesize_previous;
        		
        		if(!(diff_current > diff_previous)){
        			
        			// Slower queue polling through increasing sleeptime
                    samplerate = samplerate*factor;
                    Logging.getLogger().finest("Adjusted samplerate of sensor " + this.getName() + "to: " + samplerate);
        			
        		}else{
        			
        			// Queue size is decreasing towards its boundaries
        			diff_current = 0;
        			diff_previous = 0;
                    
        		}
        		
        	}

            // Update pollingrate if smaller or equal to samplerate
            if (pollingrate <= samplerate) {
                pollingrate = samplerate;
                Logging.getLogger().finest("Adjusted pollingrate of sensor " + this.getName() + "to: " + pollingrate);
            }
        }

    }

    // GETTERS AND SETTERS
    public ArrayList<SensorChannel> getSensorChannels() {
        return channels;
    }

    /**
     *
     * @param allowedType: type, which a SensorChannel's type have to be assignable, to be returned in the list
     * @return list, which only holds SensorChannels, whose types are assignable to the passed allowed type
     */
    public List<SensorChannel> getSensorChannels(Class allowedType) {
        List<SensorChannel> allowedChannels = new ArrayList<SensorChannel>();

        for (SensorChannel channel : channels) {
            if (allowedType.isAssignableFrom(channel.getDatatype())) {
                allowedChannels.add(channel);
            } //if allowedType is of type number, do special handling:
            //int should be assignable to long, float, double,
            //long should be assignable to float, double, etc.
            else if (Number.class.isAssignableFrom(allowedType)) {
                if (Primitives.isAssignable(allowedType, channel.getDatatype())) {
                    allowedChannels.add(channel);
                }
            }
        }

        return allowedChannels;
    }

    @Override
    public boolean equals(Object obj) {

        if (obj instanceof Sensor) {
            Sensor s = (Sensor) obj;

            if (this.pid == s.getPid() && this.server.equals(s.getServer())) {
                return true;
            } else {
                return false;
            }
        } else {
            return super.equals(obj);
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + (int) (this.pid ^ (this.pid >>> 32));
        return hash;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public String getName() {
        return name;
    }

    public long getPid() {
        return pid;
    }

    public SensorServer getServer() {
        return server;
    }

    public float getSamplerate() {
        return samplerate;
    }

    public void setSamplerate(float samplerate) {
    	this.samplerate = samplerate;
    }

    public String getStartmark() {
        return mark;
    }

    public void setStartmark(String lastmark) {
        this.mark = lastmark;
    }

    public int getMeasQueue_max() {
        return queuesize_max;
    }

    public int getMeasQueue_min() {
        return queuesize_min;
    }

    public int getMeasQueueCurrentSize() {
        return queuesize_current;
    }

    public void setMeasQueueCurrentSize(int currentsize) {
    	queuesize_previous = this.queuesize_current;
        this.queuesize_current = currentsize;
        checkQueueSizeAndSamplerate();
    }

	public float getPollingrate() {
		return pollingrate;
	}

	public void setPollingrate(float pollingrate) {
		this.pollingrate = pollingrate;
	}

	public boolean isQueueUpdated() {
		return queue_updated;
	}

	public void setQueueUpdated(boolean meas_added) {
		queue_updated = meas_added;
	}

	public boolean isSampling() {
		return sampling_started;
	}
	
	
   
}

