package peripheral.logic.action;

import java.util.Observable;
import peripheral.logic.positioningtool.ActionTool; 
import peripheral.logic.symboladapter.SymbolAdapter;
import peripheral.logic.value.UserInput;

public class RegionHideAction extends RegionAction {

    public RegionHideAction (SymbolAdapter adapter) {
        super(adapter);
    }

    public java.util.List<UserInput> getUserInput () {
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
     *        if ( !((Region)tool).isHidden() ){<br>
     *      </p>
     *      <p style="margin-top: 0">
     *        for (Symbol s : tool.getSymbols()){<br>Visualization.hide(s);<br>}<br>}
     *  <br>
     *      </p>
     *      <p style="margin-top: 0">
     *        ((Region)tool).setHidden(true);
     *      </p>
     */
    public void execute (ActionTool tool) {
    }

    public void update (Observable o, Object arg) {
    }

}

