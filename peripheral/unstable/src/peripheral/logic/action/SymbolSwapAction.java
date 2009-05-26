package peripheral.logic.action;

import peripheral.logic.symboladapter.Symbol; 
import peripheral.logic.value.UserInput;
import peripheral.logic.value.Value; 

public class SymbolSwapAction extends SymbolAction {

    private Value filename;

    public SymbolSwapAction () {
    }

    public SymbolSwapAction (Value filename) {
        this.filename = filename;
    }

    public String getFilename () {
        return null;
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
     *    Visualization.swap(s, file);
     *      </p>
     */
    public void execute (Symbol s) {
    }

}

