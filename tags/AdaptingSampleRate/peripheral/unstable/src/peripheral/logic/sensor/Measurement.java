package peripheral.logic.sensor;


public class Measurement {

    private String mark;
    private Object value;
    private String timestamp;

    public Measurement (String mark, Object value, String timestamp) {
    	this.mark = mark;
    	this.value = value;
    	this.timestamp = timestamp;
    }
    
    // GETTERS AND SETTERS
    public String getMark () {
        return mark;
    }

    public String getTimestamp () {
        return timestamp;
    }

    public Object getValue () {
        return value;
    }

}

