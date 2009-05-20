package peripheral.logic.rule;


public abstract class ContainedByCondition extends ConditionOperation {

    private float upperLimit;

    public ContainedByCondition () {
    }

    public boolean eval (Condition cond) {
        return true;
    }

    public String getOperationName () {
        return null;
    }

    public java.awt.Component getRightSideComponent () {
        return null;
    }

    public float getUpperLimit () {
        return upperLimit;
    }

    public void setUpperLimit (float val) {
        this.upperLimit = val;
    }

}

