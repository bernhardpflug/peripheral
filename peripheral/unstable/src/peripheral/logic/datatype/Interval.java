package peripheral.logic.datatype;

public class Interval {

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
}

