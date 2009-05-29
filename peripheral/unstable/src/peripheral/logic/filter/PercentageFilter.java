package peripheral.logic.filter;

import peripheral.logic.Logging;
import peripheral.logic.datatype.Interval;
import peripheral.logic.datatype.Percentage;
import peripheral.logic.symboladapter.SymbolAdapter;
import peripheral.logic.value.ConstValue;

public class PercentageFilter extends Filter {

    /*public PercentageFilter (SymbolAdapter adapter, Interval interval) {
    super(adapter);
    this.interval = interval;
    }

    public PercentageFilter(SymbolAdapter adapter, SensorValue sensorVal) {
    super(adapter);
    this.sensorVal = sensorVal;
    }*/
    public PercentageFilter(SymbolAdapter adapter, String outputVarName) {
        super(adapter, outputVarName);

        FilterInput fi = new FilterInput("inputValue", new Class[]{Number.class});
        filterInputs.put(fi.getName(), fi);

        fi = new FilterInput("interval", new Class[]{Interval.class});
        filterInputs.put(fi.getName(), fi);

        outputValue = new ConstValue(adapter, outputVarName, new Percentage(), Percentage.class);
    }

    @Override
    public void doFilter() {
        double val = ((Number) getFilterInputValue("inputValue")).doubleValue();
        Interval interval = ((Interval) getFilterInputValue("interval"));

        Percentage percentage = interval.getPercentage(val);
        outputValue.setValue(percentage);

        Logging.getLogger().finer("inputValue=" + val + "; interval=" + interval + "; percentage=" + percentage);
    }

    /*public List<java.lang.Class> getAcceptedInputTypes() {
        List<Class> acc = new ArrayList<Class>();

        acc.add(int.class);
        acc.add(float.class);
        acc.add(double.class);

        return acc;
    }

    public java.lang.Class getOutputType() {
        return Percentage.class;
    }

    public Interval getInterval() {
        return interval;
    }*/

}

