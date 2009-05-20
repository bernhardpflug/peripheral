package peripheral.logic.sensor;


public class SensorAttribute {

    private int mid;

    private Sensor sensor;

    private java.util.Map<String,String> metadata;

    public SensorAttribute () {
    }

    public java.util.Map<String,String> getMetadata () {
        return metadata;
    }

    public void setMetadata (java.util.Map<String,String> val) {
        this.metadata = val;
    }

    public int getMid () {
        return mid;
    }

    public void setMid (int val) {
        this.mid = val;
    }

    public Sensor getSensor () {
        return sensor;
    }

    public void setSensor (Sensor val) {
        this.sensor = val;
    }

}

