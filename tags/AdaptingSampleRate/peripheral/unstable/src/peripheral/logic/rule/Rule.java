package peripheral.logic.rule;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import peripheral.logic.action.ActionToolAction;
import peripheral.logic.symboladapter.SymbolAdapter;
import peripheral.logic.value.UserInput;


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

    public SymbolAdapter getAdapter() {
        return adapter;
    }

    public List<UserInput> getUserInput (){
        List<UserInput> ui = new ArrayList<UserInput>();
        for (ActionToolAction action : this.actions){
            ui.addAll(action.getUserInput());
        }
        return ui;
    }

    public String getPrefix (Condition condition){
        return "rule" + adapter.getRules().indexOf(this) + ".condition" + this.conditions.indexOf(condition) + ".";
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

    public ArrayList<Condition> getInvalidConditions() {

        ArrayList<Condition> result = new ArrayList<Condition>();

        for (Condition condition : conditions) {
            if (!condition.isValid()) {
                result.add(condition);
            }
        }

        return result;
    }
}

