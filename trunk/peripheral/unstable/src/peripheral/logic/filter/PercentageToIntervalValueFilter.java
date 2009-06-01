/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package peripheral.logic.filter;

import java.awt.Point;
import peripheral.logic.Logging;
import peripheral.logic.datatype.Interval;
import peripheral.logic.datatype.Percentage;
import peripheral.logic.positioningtool.Line;
import peripheral.logic.symboladapter.SymbolAdapter;
import peripheral.logic.value.ConstValue;

/**
 *
 * @author Andi
 */
public class PercentageToIntervalValueFilter extends Filter{
    public PercentageToIntervalValueFilter(SymbolAdapter adapter, String outputVarName) {
        super(adapter, outputVarName);

        FilterInput fi = new FilterInput("percentage", new Class[]{Percentage.class});
        filterInputs.put(fi.getName(), fi);

        fi = new FilterInput("interval", new Class[]{Interval.class});
        filterInputs.put(fi.getName(), fi);

        outputValue = new ConstValue(adapter, outputVarName, 0.0, Double.class);
    }

    @Override
    public void doFilter() {
        Percentage percentage = (Percentage) getFilterInputValue("percentage");
        Interval interval = (Interval) getFilterInputValue("interval");

        double value = interval.getValue(percentage);
        outputValue.setValue(value);

        Logging.getLogger().finer("percentage=" + percentage + "; interval=" + interval + "; value=" + value);
    }
}
