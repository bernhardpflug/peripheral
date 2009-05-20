package peripheral.logic.action;

import peripheral.logic.positioningtool.ActionTool; 

public abstract class ActionToolAction extends Action {

    public ActionToolAction () {
    }

    public abstract void execute (ActionTool tool);

}

