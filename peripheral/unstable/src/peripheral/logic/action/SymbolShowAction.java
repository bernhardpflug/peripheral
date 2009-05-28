package peripheral.logic.action;

import java.util.ArrayList;
import peripheral.logic.symboladapter.Symbol; 
import peripheral.logic.symboladapter.SymbolAdapter;
import peripheral.logic.value.UserInput;

public class SymbolShowAction extends SymbolAction {

    public SymbolShowAction (SymbolAdapter adapter) {
        super(adapter);
    }

    public java.util.List<UserInput> getUserInput () {
        return new ArrayList<UserInput>();
    }

    public String getDescription () {
        return "Shows a symbol";
    }

    public void execute (Symbol s) {
        peripheral.logic.Runtime.getInstance().getVisualization().show(s);
    }

}

