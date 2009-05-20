package peripheral.logic.rule;


public abstract class EqualCondition extends ConditionOperation {

    public EqualCondition () {
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

