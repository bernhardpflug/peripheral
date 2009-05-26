package peripheral.logic.datatype;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;


public class Interval {

    private double lowerBound;

    private double upperBound;

    public Interval () {
    }

    public Interval (double lowerBound, double upperBound) {
    }

    public double getLowerBound () {
        return lowerBound;
    }

    public void setLowerBound (double val) {
        this.lowerBound = val;
    }

    public double getUpperBound () {
        return upperBound;
    }

    public void setUpperBound (double val) {
        this.upperBound = val;
    }

    public Percentage getPercentage (double val){
        throw new NotImplementedException();
    }
}

