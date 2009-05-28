package peripheral.logic.value;

import peripheral.logic.datatype.Interval;
import peripheral.logic.sensor.SensorChannel;
import peripheral.logic.symboladapter.SymbolAdapter;

public class SensorValue extends Value {

    private SensorChannel sensorChannel;
    private Object actValue;

    /**
     *  <p style="margin-top: 0">
     *    sensorChannel.addSensorValue(this)
     *      </p>
     */
    public SensorValue(SymbolAdapter adapter, String varName, SensorChannel sensorChannel) {
        super(adapter, varName);

        setSensorChannel(sensorChannel);
    }

    public SensorValue(SymbolAdapter adapter, String varName) {
        super(adapter, varName);
    }

    public SensorChannel getSensorChannel() {
        return sensorChannel;
    }

    public void setSensorChannel(SensorChannel val) {
        this.sensorChannel = val;
        if (val != null) {
            this.sensorChannel.getSensorValues().add(this);

            //@todo: delete; only for testing purposes
            this.adapter.getPreselectedSensors().add(this.sensorChannel.getSensor());
        }
    }

    public void setActValue(Object val) {
        this.actValue = val;
    }

    public Object getValue() {
        return "100";
        //return actValue;
    }

    public Interval getLimits() {
        //return sensor limits
        return new Interval();
    }
}

