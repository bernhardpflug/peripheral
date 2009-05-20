package peripheral.logic.value;

import peripheral.logic.sensor.SensorAttribute; 
import peripheral.logic.sensor.SensorChannel;

public class SensorValue extends Value {

    private SensorChannel sensorChannel;

    private Object actValue;

    private SensorAttribute sensorAttribute;

    public SensorValue (String varName, SensorAttribute sensorAttribute) {
        super(varName);
    }

    public SensorAttribute getSensorAttribute () {
        return sensorAttribute;
    }

    public void setSensorAttribute (SensorAttribute val) {
        this.sensorAttribute = val;
    }

    public Object getValue () {
        return null;
    }

    /**
     *  <p style="margin-top: 0">
     *    sensorChannel.addSensorValue(this)
     *      </p>
     */
    public SensorValue (String varName, SensorChannel sensorChannel) {
        super(varName);
    }

    public SensorChannel getSensorChannel () {
        return sensorChannel;
    }

    public void setSensorChannel (SensorChannel val) {
        this.sensorChannel = val;
    }

    public void setActValue (Object val) {
        this.actValue = val;
    }

}

