/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package peripheral.logic.filter;

import peripheral.logic.Logging;
import peripheral.logic.symboladapter.SymbolAdapter;
import peripheral.logic.value.SensorValue;

/**
 *
 * @author Andi
 */
public class SensorValueThresholdFilter extends SensorValueFilter {

    private float oldVal = 0;
    private boolean firstChange = true;

    public SensorValueThresholdFilter(SymbolAdapter adapter, SensorValue sensorValue) throws Exception {
        super(adapter, sensorValue);

        if (!Number.class.isAssignableFrom(sensorValue.getValueType())) {
            throw new Exception("SensorValue must be of type Number.");
        }

        FilterInput fi = new FilterInput("threshold", new Class[]{Float.class});
        filterInputs.put(fi.getName(), fi);
    }

    @Override
    public void doFilter() {
        if (getApplyFilter()) {
            float threshold = (Float) getFilterInputValue("threshold");
            float val = ((Number) sensorValue.getValue()).floatValue();
            boolean change = false;

            if (firstChange) {
                firstChange = false;
                change = true;
            } else {
                change = Math.abs(oldVal - val) >= threshold;
            }

            if (change) {
                //outputValue.setValue(val);
                oldVal = val;
            }
            else{
                sensorValue.setActValue(oldVal);
            }
        }
    }
}
