package peripheral.logic.rule;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import peripheral.designer.conditions.ContainedByRightSideComponent;
import peripheral.logic.Logging;

public class ContainedByCondition extends ConditionOperation {

    private transient ContainedByRightSideComponent rightSideComponent;
    private double lowerBound;
    private double upperBound;

    public ContainedByCondition(Condition condition) {
        super(condition);
    }

    public boolean eval(Condition cond) {
        double value = Double.parseDouble(cond.getLeftSideOp().getValue().toString());

        return (value >= lowerBound) && (value <= upperBound);
    }

    public String getOperationName() {
        return "zwischen";
    }

    public java.awt.Component getRightSideComponent() {
        if (rightSideComponent == null) {
            rightSideComponent = new ContainedByRightSideComponent();
            rightSideComponent.setLowerBound(lowerBound);
            rightSideComponent.setUpperBound(upperBound);
        }
        return rightSideComponent;
    }

    @Override
    public void saveValuesFromRightSideComponent() {
        if (rightSideComponent != null) {
            lowerBound = rightSideComponent.getLowerBound();
            upperBound = rightSideComponent.getUpperBound();

            Logging.getLogger().finer("saved values: lowerBound=" + lowerBound + ", upperBound=" + upperBound);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ContainedByCondition){
            return ((ContainedByCondition)obj).getOperationName().equals(this.getOperationName());
        }
        return false;
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        saveValuesFromRightSideComponent();

        out.writeDouble(lowerBound);
        out.writeDouble(upperBound);

        out.defaultWriteObject();
    }

    private void readObject(ObjectInputStream in) throws ClassNotFoundException, IOException {
        //saveValuesFromRightSideComponent();

        lowerBound = in.readDouble();
        upperBound = in.readDouble();

        in.defaultReadObject();
    }
}

