package peripheral.logic.rule;

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
}

