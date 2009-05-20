package peripheral.logic.rule;


public abstract class TrueCondition extends ConditionOperation {

    public TrueCondition () {
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

}

