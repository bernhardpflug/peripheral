package peripheral.logic.action;

import java.util.ArrayList;
import java.util.List;
import peripheral.logic.symboladapter.Symbol; 
import peripheral.logic.symboladapter.SymbolAdapter;
import peripheral.logic.value.UserInput;

public abstract class SymbolAction extends Action {

    protected List<UserInput> userInput = new ArrayList<UserInput>();

    public SymbolAction (SymbolAdapter adapter) {
        super(adapter);
    }

    public abstract void execute (Symbol s);

    public String getName(){
        return getClass().getName();
    }

    @Override
    public List<UserInput> getUserInput() {
        return userInput;
    }


}

