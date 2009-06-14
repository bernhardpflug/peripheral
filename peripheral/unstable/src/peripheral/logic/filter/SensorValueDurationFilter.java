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
public class SensorValueDurationFilter extends SensorValueFilter {

    private float oldVal = 0;
    private float lastSetVal = 0;
    private long millisStart;
    private boolean firstChange = true;

    public SensorValueDurationFilter(SymbolAdapter adapter, SensorValue sensorValue) throws Exception {
        super(adapter, sensorValue);

        if (!Number.class.isAssignableFrom(sensorValue.getValueType())) {
            throw new Exception("SensorValue must be of type Number.");
        }

        FilterInput fi = new FilterInput("duration", new Class[]{Float.class});
        filterInputs.put(fi.getName(), fi);

        fi = new FilterInput("threshold", new Class[]{Float.class});
        filterInputs.put(fi.getName(), fi);
    }

    @Override
    public void doFilter() {
        if (getApplyFilter()) {
            float duration = (Float) getFilterInputValue("duration");
            float threshold = (Float) getFilterInputValue("threshold");
            float val = ((Number) sensorValue.getValue()).floatValue();
            boolean change = false;
            float diff = oldVal - val;

            oldVal = val;

            if (firstChange) {
                firstChange = false;
                change = true;
                millisStart = System.currentTimeMillis();
            } else {
                if (Math.abs(diff) > threshold) {
                    millisStart = System.currentTimeMillis();
                } else {
                    change = (System.currentTimeMillis() - millisStart) >= (duration * 1000);
                }
            }

            if (change) {
                lastSetVal = val;
            } else {
                sensorValue.setActValue(lastSetVal);
            }
        }
    }
}
