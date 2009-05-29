package peripheral.logic.rule;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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

    public java.util.List<ConditionOperation> getAvailableOperations () {
        List<ConditionOperation> ops = new ArrayList<ConditionOperation>();

        if (leftSideOp == null){
            return ops;
        }

        Class type = leftSideOp.getSensorChannel().getDatatype();

        if ( isString(type) || isNumber(type) ){
            for (SimpleCondition.Operation simpleOp : SimpleCondition.Operation.values()){
                ops.add(new SimpleCondition(simpleOp));
            }
        }

        if (isNumber(type)){
            ops.add(new ContainedByCondition());
        }

        return ops;
    }

    private boolean isNumber (Class type){
        return type.equals(int.class) ||type.equals(long.class) || type.equals(float.class) || type.equals(double.class);
    }

    private boolean isString (Class type){
        return type.equals(String.class);
    }

    public boolean eval () {
        return getOperation().eval(this);
    }

}

