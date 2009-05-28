package peripheral.logic.action;

import peripheral.logic.symboladapter.Symbol; 
import peripheral.logic.symboladapter.SymbolAdapter;

public abstract class SymbolAction extends Action {

    public SymbolAction (SymbolAdapter adapter) {
        super(adapter);
    }

    public void execute (Symbol s) {
    }

}

