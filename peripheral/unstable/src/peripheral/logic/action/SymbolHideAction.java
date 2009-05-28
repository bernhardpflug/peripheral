package peripheral.logic.action;

import peripheral.logic.symboladapter.Symbol; 
import peripheral.logic.symboladapter.SymbolAdapter;
import peripheral.logic.value.UserInput;

public class SymbolHideAction extends SymbolAction {

    public SymbolHideAction (SymbolAdapter adapter) {
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
     *    Visualization.hide(s);
     *      </p>
     */
    public void execute (Symbol s) {
    }

}

