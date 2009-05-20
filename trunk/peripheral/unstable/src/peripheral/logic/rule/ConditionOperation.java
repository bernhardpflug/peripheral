package peripheral.logic.rule;


public abstract class ConditionOperation {

    private int Unnamed;

    private Object rightSideOp;

    public ConditionOperation () {
    }

    public abstract boolean eval (Condition cond);

    public abstract String getOperationName ();

    public abstract java.awt.Component getRightSideComponent ();

    public String toString () {
        return null;
    }

    public Object getRightSideOp () {
        return rightSideOp;
    }

    public void setRightSideOp (Object val) {
        this.rightSideOp = val;
    }

}

