package peripheral.logic.sensor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import peripheral.logic.datatype.Interval;
import peripheral.logic.value.SensorValue;


public class SensorChannel implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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

    public Class getDatatype(){
        String jddacType = metadata.get("datatype");

        if (jddacType.equalsIgnoreCase("String")){
            return String.class;
        }
        else if (jddacType.equalsIgnoreCase("Integer64")){
            return long.class;
        }
        else if (jddacType.startsWith("Integer")){
            return int.class;
        }
        else if (jddacType.equalsIgnoreCase("Float32")){
            return float.class;
        }
        else if (jddacType.equalsIgnoreCase("Float64")){
            return double.class;
        }

        return Object.class;
    }

    public Interval getBounds (){
        double lower = 0, upper = 0;
        try{
            lower = Double.parseDouble(metadata.get("lowerlimit").toString());
        }catch (Exception e){
            e.printStackTrace();
        }
        try{
            upper = Double.parseDouble(metadata.get("upperlimit").toString());
        }catch (Exception e){
            e.printStackTrace();
        }

        Interval bounds;
        try{
            bounds = new Interval(lower, upper);
        }catch (Exception e){
            e.printStackTrace();
            bounds = new Interval();
        }
        return bounds;
    }

}

