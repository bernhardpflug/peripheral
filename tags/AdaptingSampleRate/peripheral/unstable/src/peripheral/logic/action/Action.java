package peripheral.logic.action;

import java.io.Serializable;
import peripheral.logic.symboladapter.SymbolAdapter;
import peripheral.logic.value.UserInput;


public abstract class Action implements Serializable {

    protected SymbolAdapter adapter;

    public Action (SymbolAdapter adapter) {
        this.adapter = adapter;
    }

    public abstract java.util.List<UserInput> getUserInput ();

    public abstract String getName ();

    public abstract String getDescription ();

    public SymbolAdapter getAdapter() {
        return adapter;
    }


}

