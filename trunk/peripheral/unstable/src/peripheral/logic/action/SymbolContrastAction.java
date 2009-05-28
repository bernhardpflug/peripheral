package peripheral.logic.action;

import peripheral.logic.symboladapter.Symbol; 
import peripheral.logic.symboladapter.SymbolAdapter;
import peripheral.logic.value.UserInput;
import peripheral.logic.value.Value; 

public class SymbolContrastAction extends SymbolAction {

    private Value amount;

    public SymbolContrastAction (SymbolAdapter adapter) {
        super(adapter);
    }

    public SymbolContrastAction (SymbolAdapter adapter, Value amount) {
        this(adapter);
        this.amount = amount;
    }

    public float getAmount () {
        return 0;
        //extract concrete value from Value-Object
        //return amount;
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
     *    Visualization.contrast(s, amount);
     *      </p>
     */
    public void execute (Symbol s) {
    }

}
