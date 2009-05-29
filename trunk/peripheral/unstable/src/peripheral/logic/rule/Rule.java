package peripheral.logic.rule;

import java.io.Serializable;
import java.util.ArrayList;
import peripheral.logic.action.ActionToolAction;
import peripheral.logic.symboladapter.SymbolAdapter;


public class Rule implements Serializable{

    private java.util.List<Condition> conditions;

    private java.util.List<ActionToolAction> actions;

    private SymbolAdapter adapter;

    public Rule (SymbolAdapter adapter) {
        this.adapter = adapter;
        conditions = new ArrayList<Condition>();

        actions = new ArrayList<ActionToolAction>();
    }

    public java.util.List<ActionToolAction> getActions () {
        return actions;
    }

    /*public void addAction(ActionToolAction action){

    }*/

    public java.util.List<Condition> getConditions () {
        return conditions;
    }

    public boolean tryExecute (){
        boolean execute = true;

        for (Condition cond : getConditions()){
            execute = execute && cond.eval();
        }

        if (execute){
            for (ActionToolAction a : actions){
                a.execute(adapter.getTool());
            }
        }

        return execute;
    }
}

