package peripheral.logic.sensor;

import java.util.TreeMap;


public class SensorChannel {

    private long mid;

    private String fullname;

    private Sensor Sensor;

    private java.util.concurrent.ConcurrentLinkedQueue<Measurement> measQueue;

    private java.util.TreeMap<String,String> metadata;

    public SensorChannel (long mid, String fullname, Sensor sensor, TreeMap<String,String> metadata) {
    }

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

    public void addMeasurementToQueue (Measurement meas) {
    }

    public void flushMeasQueue () {
    }

    public Sensor getSensor () {
        return Sensor;
    }

}

