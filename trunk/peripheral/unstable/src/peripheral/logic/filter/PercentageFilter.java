package peripheral.logic.filter;

import java.util.ArrayList; 
import java.util.List;
import peripheral.logic.datatype.Interval;
import peripheral.logic.datatype.Percentage;
import peripheral.logic.symboladapter.SymbolAdapter;
import peripheral.logic.value.ConstValue;
import peripheral.logic.value.SensorValue;

public class PercentageFilter extends Filter {

    private Interval interval = null;
    private SensorValue sensorVal = null;

    public PercentageFilter (SymbolAdapter adapter, Interval interval) {
        super(adapter);
        this.interval = interval;
    }

    public PercentageFilter(SymbolAdapter adapter, SensorValue sensorVal) {
        super(adapter);
        this.sensorVal = sensorVal;
    }

    @Override
    public Object doFilter () {
        double val = ((Number)getInputValue()).doubleValue();

        if (interval == null){
            interval = sensorVal.getLimits();
        }

        Percentage percentage = interval.getPercentage(val);
        new ConstValue(adapter, this.getOutputVarName(), percentage);
        return percentage;
    }

    public List<java.lang.Class> getAcceptedInputTypes () {
        List<Class> acc = new ArrayList<Class>();

        acc.add(int.class);
        acc.add(float.class);
        acc.add(double.class);

        return acc;
    }

    public java.lang.Class getOutputType () {
        return Percentage.class;
    }

    public Interval getInterval () {
        return interval;
    }

    public void setInterval (Interval val) {
        this.interval = val;
    }

}

