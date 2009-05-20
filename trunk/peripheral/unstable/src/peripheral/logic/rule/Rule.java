package peripheral.logic.rule;

import java.util.ArrayList;
import peripheral.logic.action.Action;


public class Rule {

    private java.util.List<Condition> conditions;

    private java.util.List<Action> actions;

    public Rule () {

        conditions = new ArrayList<Condition>();

        actions = new ArrayList<Action>();
    }

    public java.util.List<Action> getActions () {
        return actions;
    }

    public java.util.List<Condition> getConditions () {
        return conditions;
    }

    public void setConditions (java.util.List<Condition> val) {
        this.conditions = val;
    }

    public void addAction (Action action) {
    }

}

