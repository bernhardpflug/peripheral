package peripheral.logic.filter;

import java.util.ArrayList; 
import peripheral.logic.datatype.Interval;

public class PercentageFilter extends Filter {

    private Interval interval;

    public PercentageFilter (Interval interrval) {
    }

    public Object doFilter (Object val) {
        return null;
    }

    public ArrayList<java.lang.Class> getAcceptedInputTypes () {
        return null;
    }

    public java.lang.Class getOutputType () {
        return null;
    }

    public Interval getInterval () {
        return interval;
    }

    public void setInterval (Interval val) {
        this.interval = val;
    }

}

