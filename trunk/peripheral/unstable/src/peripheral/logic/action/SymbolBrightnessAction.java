package peripheral.logic.action;

import peripheral.logic.symboladapter.Symbol; 
import peripheral.logic.symboladapter.SymbolAdapter;
import peripheral.logic.value.UserInput;
import peripheral.logic.value.Value; 

public class SymbolBrightnessAction extends SymbolAction {

    private Value amount;

    public SymbolBrightnessAction (SymbolAdapter adapter) {
        super(adapter);
    }

    public SymbolBrightnessAction (SymbolAdapter adapter, Value amount) {
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
     *    Visualization.brightness(s, amount);
     *      </p>
     *      <p style="margin-top: 0">
     *    <br>
     *      </p>
     */
    public void execute (Symbol s) {
    }

}

