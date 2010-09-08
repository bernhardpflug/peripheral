package peripheral.logic.sensor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import peripheral.logic.datatype.Interval;
import peripheral.logic.value.SensorValue;

public class SensorChannel implements Serializable {

    private static SensorChannel DUMMY;
    private static final long serialVersionUID = 1L;
    private long mid;
    private String fullname;
    private Sensor sensor;
    private ConcurrentLinkedQueue<Measurement> measQueue;
    private TreeMap<String, String> metadata;
    private List<SensorValue> sensorValues;

    public SensorChannel(long mid, String fullname, Sensor sensor) {
        this.mid = mid;
        this.fullname = fullname;
        this.sensor = sensor;
        this.metadata = new TreeMap<String, String>();

        this.sensorValues = new ArrayList<SensorValue>();
        this.measQueue = new ConcurrentLinkedQueue<Measurement>();
    }

    // GETTERS AND SETTERS
    public String getFullname() {
        return fullname;
    }

    public java.util.concurrent.ConcurrentLinkedQueue<Measurement> getMeasQueue() {
        return measQueue;
    }

    public TreeMap<String, String> getMetadata() {
        return metadata;
    }

    public long getMid() {
        return mid;
    }

    public void flushMeasQueue() {
    }

    public Sensor getSensor() {
        return sensor;
    }

    public List<SensorValue> getSensorValues() {
        return sensorValues;
    }

    public Object getParsedValue(String value) {
        Double val = Double.valueOf(value);
        if (this.getDatatype().equals(Long.class)) {
            return val.longValue();
        } else if (this.getDatatype().equals(Integer.class)) {
            return val.intValue();
        } else if (this.getDatatype().equals(Float.class)) {
            return val.floatValue();
        } else if (this.getDatatype().equals(Double.class)) {
            return val;
        }
        return value;
    }

    public Class getDatatype() {
        String jddacType = metadata.get("datatype");

        if (jddacType.equalsIgnoreCase("String")) {
            return String.class;
        } else if (jddacType.equalsIgnoreCase("Integer64")) {
            return Long.class;
        } else if (jddacType.startsWith("Integer")) {
            return Integer.class;
        } else if (jddacType.equalsIgnoreCase("Float32")) {
            return Float.class;
        } else if (jddacType.equalsIgnoreCase("Float64")) {
            return Double.class;
        }

        return Object.class;
    }

    public Interval getBounds() {
        double lower = 0, upper = 0;
        try {
            lower = Double.parseDouble(metadata.get("lowerlimit").toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            upper = Double.parseDouble(metadata.get("upperlimit").toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        Interval bounds;
        try {
            bounds = new Interval(lower, upper);
        } catch (Exception e) {
            e.printStackTrace();
            bounds = new Interval();
        }
        return bounds;
    }

    public String toString() {
        if (metadata.get("shortname") != null) {
            return metadata.get("shortname");
        }
        else {
            return this.fullname;
        }
    }

    @Override
    public boolean equals(Object obj) {

        if (obj instanceof SensorChannel) {
            SensorChannel sc = (SensorChannel) obj;

            if (this.mid == sc.getMid() && this.sensor.equals(sc.getSensor())) {
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
        int hash = 5;
        hash = 29 * hash + (int) (this.mid ^ (this.mid >>> 32));
        return hash;
    }

    /**
     * returns dummy sensor that is used to represent no valid selected sensor
     * there must not be a server named similar to the dummyserver as this might
     * cause conflicts when deleting similar named server from server list
     * @return
     */
    public static SensorChannel getDummy() {

        if (DUMMY == null) {
            SensorServer dummyserver = new SensorServer("dummyserver","8080","dummy");
            Sensor dummysensor = new Sensor(-2,"dummysensor",dummyserver);
            DUMMY = new SensorChannel(-1, "DUMMY", dummysensor) {

                public String toString() {
                    return "<none>";
                }

                public Class getDatatype() {
                    return Object.class;
                }
            };
        }

        return DUMMY;
    }
}

