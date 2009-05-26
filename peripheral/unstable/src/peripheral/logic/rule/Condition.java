package peripheral.logic.rule;

import java.io.Serializable;
import peripheral.logic.value.SensorValue; 

public class Condition implements Serializable {

    private SensorValue leftSideOp;

    private ConditionOperation operation;

    public Condition () {
    }

    public SensorValue getLeftSideOp () {
        return leftSideOp;
    }

    public void setLeftSideOp (SensorValue val) {
        this.leftSideOp = val;
    }

    public ConditionOperation getOperation () {
        return operation;
    }

    public void setOperation (ConditionOperation val) {
        this.operation = val;
    }

    public java.util.List<ConditionOperation> getAvailableOperations (Class type) {
        return null;
    }

    public boolean eval () {
        return true;
    }

}

