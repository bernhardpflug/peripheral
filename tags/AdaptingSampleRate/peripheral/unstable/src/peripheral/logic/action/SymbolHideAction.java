package peripheral.logic.action;

import java.util.ArrayList;
import peripheral.logic.symboladapter.Symbol; 
import peripheral.logic.symboladapter.SymbolAdapter;
import peripheral.logic.value.UserInput;

public class SymbolHideAction extends SymbolAction {

    public SymbolHideAction (SymbolAdapter adapter) {
        super(adapter);
    }

    public String getDescription () {
        return "Hides a symbol";
    }

    public void execute (Symbol s) {
        peripheral.logic.Runtime.getInstance().getVisualization().hideSymbol(s);
    }

}

