package peripheral.logic.action;

import java.util.Observable;
import peripheral.logic.positioningtool.ActionTool; 
import peripheral.logic.positioningtool.PositioningTool; 
import peripheral.logic.value.UserInput;

public class ListToolAction extends ListAction {

    private PositioningTool tool;

    /**
     *  <p style="margin-top: 0">
     *        may only hold the following types:<br>if tool instanceof Point: 
     *        PointAction
     *  <br>if tool instanceof Region: RegionAction
     *      </p>
     */
    private ActionToolAction action;

    public ListToolAction (ActionToolAction action) {
    }

    public java.util.Set<UserInput> getUserInput () {
        return null;
    }

    public String getName () {
        return null;
    }

    public String getDescription () {
        return null;
    }

    /**
     *  <p style="margin-top: 0">
     *        action.execute(tool);
     *      </p>
     */
    public void execute (ActionTool tool) {
    }

    public void update (Observable o, Object arg) {
    }

}

