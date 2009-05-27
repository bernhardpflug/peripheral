package peripheral.logic.sensor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import peripheral.logic.value.SensorValue;


public class SensorChannel implements Serializable{

    private long mid;
    private String fullname;
    private Sensor sensor;
    private ConcurrentLinkedQueue<Measurement> measQueue;
    private TreeMap<String,String> metadata;
    private List<SensorValue> sensorValues;

    public SensorChannel (long mid, String fullname, Sensor sensor) {
    	this.mid = mid;
    	this.fullname = fullname;
    	this.sensor = sensor;
    	this.metadata = new TreeMap<String, String>();

        this.sensorValues = new ArrayList<SensorValue>();
    	this.measQueue = new ConcurrentLinkedQueue<Measurement>();
    }

    // GETTERS AND SETTERS
    public String getFullname () {
        return fullname;
    }

    public java.util.concurrent.ConcurrentLinkedQueue<Measurement> getMeasQueue () {
        return measQueue;
    }

    public TreeMap<String,String> getMetadata () {
        return metadata;
    }

    public long getMid () {
        return mid;
    }

    public void flushMeasQueue () {
    }

    public Sensor getSensor () {
        return sensor;
    }

    public List<SensorValue> getSensorValues() {
        return sensorValues;
    }

}

