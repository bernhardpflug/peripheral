package peripheral.logic.action;

import peripheral.logic.symboladapter.Symbol; 
import peripheral.logic.symboladapter.SymbolAdapter;

public abstract class SymbolAction extends Action {

    public SymbolAction (SymbolAdapter adapter) {
        super(adapter);
    }

    public abstract void execute (Symbol s);

    public String getName(){
        return getClass().getName();
    }

}

