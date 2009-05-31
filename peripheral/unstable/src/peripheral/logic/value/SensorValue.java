package peripheral.logic.value;

import peripheral.logic.datatype.Interval;
import peripheral.logic.sensor.SensorChannel;
import peripheral.logic.symboladapter.SymbolAdapter;

public class SensorValue extends Value {

    private SensorChannel sensorChannel;
    private Object actValue;

    public SensorValue(SymbolAdapter adapter, String varName, Class valueType) {
        super(adapter, varName, valueType);

        setSensorChannel(SensorChannel.getDummy());
    }

    /*public SensorValue(SymbolAdapter adapter, String varName, SensorChannel sensorChannel) {
        this(adapter, varName, sensorChannel, null);
    }*/

    public SensorChannel getSensorChannel() {
        return sensorChannel;
    }

    public void setSensorChannel(SensorChannel val) {
        this.sensorChannel = val;
        if (val != null) {
            this.sensorChannel.getSensorValues().add(this);

        }
    }

    public void setActValue(Object val) {
        this.actValue = val;
    }

    public Object getValue() {
        //@todo: return real actvalue
        return 100;
    //return actValue;
    }

    public Interval getBounds() {
        return sensorChannel.getBounds();
    }
}

