package peripheral.logic.rule;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import peripheral.logic.sensor.SensorChannel;
import peripheral.logic.value.SensorValue;

public class Condition implements Serializable {

    private SensorValue leftSideOp;
    private ConditionOperation operation;

    public Condition() {
    }

    public SensorValue getLeftSideOp() {
        return leftSideOp;
    }

    public void setLeftSideOp(SensorValue val) {
        this.leftSideOp = val;
    }

    public ConditionOperation getOperation() {
        return operation;
    }

    public void setOperation(ConditionOperation val) {
        this.operation = val;
    }

    public java.awt.Component getRightSideComponent() {
        if (operation == null) {
            return new JLabel("Select operation first.");
        }
        return operation.getRightSideComponent();
    }

    public java.util.List<ConditionOperation> getAvailableOperations() {
        List<ConditionOperation> ops = new ArrayList<ConditionOperation>();

        if (leftSideOp == null) {
            return ops;
        }

        if (isLeftSideOpTypeString() || isLeftSideOpTypeNumber()) {
            for (SimpleCondition.Operation simpleOp : SimpleCondition.Operation.values()) {
                ops.add(new SimpleCondition(this, simpleOp));
            }
        }

        if (isLeftSideOpTypeNumber()) {
            ops.add(new ContainedByCondition(this));
        }

        return ops;
    }

    public boolean isLeftSideOpTypeNumber() {
        return isNumber(leftSideOp.getSensorChannel().getDatatype());
    }

    public boolean isLeftSideOpTypeString() {
        return isString(leftSideOp.getSensorChannel().getDatatype());
    }

    private static boolean isNumber(Class type) {
        return type.equals(Integer.class) || type.equals(Long.class) || type.equals(Float.class) || type.equals(Double.class);
    }

    private static boolean isString(Class type) {
        return type.equals(String.class);
    }

    public boolean eval() {
        return getOperation().eval(this);
    }

    public boolean isValid() {

        return this.leftSideOp.isValid();
    }
}

