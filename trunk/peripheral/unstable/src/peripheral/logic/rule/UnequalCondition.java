package peripheral.logic.rule;


public abstract class UnequalCondition extends ConditionOperation {

    public UnequalCondition () {
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

