package peripheral.logic.rule;


public class TrueCondition extends Condition {

    public TrueCondition () {
    }

    public boolean eval (Condition cond) {
        return true;
    }

}

