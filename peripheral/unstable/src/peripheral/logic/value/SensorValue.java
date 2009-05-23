package peripheral.logic.value;

import peripheral.logic.sensor.SensorChannel;

public class SensorValue extends Value {

    private SensorChannel sensorChannel;

    private Object actValue;

    /**
     *  <p style="margin-top: 0">
     *    sensorChannel.addSensorValue(this)
     *      </p>
     */
    public SensorValue (String varName, SensorChannel sensorChannel) {
        super(varName);

        this.sensorChannel = sensorChannel;
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

    public Object getValue () {
        return actValue;
    }


}

