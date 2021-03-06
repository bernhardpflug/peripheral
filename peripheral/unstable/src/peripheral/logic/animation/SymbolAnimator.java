package peripheral.logic.animation;

import java.io.Serializable;
import java.util.List;
import peripheral.logic.symboladapter.SymbolAdapter;
import peripheral.logic.value.UserInput;
import peripheral.viz.Visualization;
import peripheral.viz.VisSymbol;
import peripheral.viz.VisAnimator;


public abstract class SymbolAnimator implements Serializable {
    protected SymbolAdapter adapter;
    
    protected SymbolAnimator (SymbolAdapter adapter){
        this.adapter = adapter;
    }
    
    public abstract List<UserInput> getUserInput();

    public SymbolAdapter getAdapter() {
        return adapter;
    }

    public abstract VisAnimator getVisAnimator (Visualization viz, VisSymbol symbol);
}

