package peripheral.logic.action;

import java.util.Observable;
import peripheral.logic.positioningtool.ActionTool; 
import peripheral.logic.value.UserInput;

public class RegionShowAction extends RegionAction {

    public RegionShowAction () {
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
     *        if ( ((Region)tool).isHidden() ){<br>
     *      </p>
     *      <p style="margin-top: 0">
     *        for (Symbol s : tool.getSymbols()){<br>Visualization.show(s);<br>}<br>}
     *  <br>((Region)tool).setHidden(false);
     *      </p>
     */
    public void execute (ActionTool tool) {
    }

    public void update (Observable o, Object arg) {
    }

}

