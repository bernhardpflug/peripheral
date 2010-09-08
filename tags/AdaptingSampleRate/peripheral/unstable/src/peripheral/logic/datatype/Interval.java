package peripheral.logic.datatype;

import java.io.Serializable;

public class Interval implements Serializable {

    private double lowerBound = 0;
    private double upperBound = 0;

    public Interval() {
    }

    public Interval(double lowerBound, double upperBound) throws Exception {
        setBounds(lowerBound, upperBound);
    }

    public double getLowerBound() {
        return lowerBound;
    }

    public double getUpperBound() {
        return upperBound;
    }

    public void setBounds(double lowerBound, double upperBound) throws Exception {
        if (lowerBound > upperBound) {
            throw new Exception("Lower bound must be lower or equal then upper bound.");
        }

        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }

    public Percentage getPercentage(double value) {
        double offset = 0;
        if (lowerBound < 0){
            offset = -lowerBound;
        }

        double low = lowerBound + offset;
        double up = upperBound + offset;
        double val = value + offset;

        return new Percentage(val/(up-low));
    }

    public double getValue (Percentage percentage){
        double offset = 0;
        if (lowerBound < 0){
            offset = -lowerBound;
        }

        double low = lowerBound + offset;
        double up = upperBound + offset;
        double size = up - low;

        return percentage.getVal() * size - offset;
    }

    @Override
    public String toString() {
        return "[" + lowerBound + "," + upperBound + "]";
    }


}

