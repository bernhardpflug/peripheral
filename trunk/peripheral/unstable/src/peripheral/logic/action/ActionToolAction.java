package peripheral.logic.action;

import peripheral.logic.positioningtool.ActionTool; 
import peripheral.logic.symboladapter.SymbolAdapter;

public abstract class ActionToolAction extends Action {

    public ActionToolAction (SymbolAdapter adapter) {
        super(adapter);
    }

    public abstract void execute (ActionTool tool);


}

