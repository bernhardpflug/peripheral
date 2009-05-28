package peripheral.logic.action;

import java.util.Observable;
import peripheral.logic.positioningtool.ActionTool; 
import peripheral.logic.symboladapter.Symbol; 
import peripheral.logic.symboladapter.SymbolAdapter;
import peripheral.logic.value.UserInput;
import peripheral.logic.value.Value; 

public abstract class RegionWrapperAction extends RegionAction {

    private Value symbol;

    private SymbolAction symbolAction;

    private RegionWrapperAction (SymbolAdapter adapter, SymbolAction symbolAction) {
        super(adapter);
        this.symbolAction = symbolAction;
    }

    public java.util.List<UserInput> getUserInput () {
        return null;
    }

    public abstract String getName ();

    public abstract String getDescription ();

    /**
     *  <p style="margin-top: 0">
     *        symbolAction.execute(symbol);
     *      </p>
     */
    public void execute (ActionTool tool) {
    }

    /**
     *  <p style="margin-top: 0">
     *    returns anonymous class based on the passed SymbolAction.<br><br>e.g.:<br>return 
     *    new RegionWrapperAction(symbolAction){<br>public String getName(){...}<br>public 
     *    String getDescription(){...<br>}
     *      </p>
     */
    public RegionWrapperAction getRegionWrapperAction (SymbolAction symbolAction) {
        return null;
    }

    public void update (Observable o, Object arg) {
    }

    public void getRegionWrapperAction (SymbolAction symbolAction, Value symbol) {
    }

    public Symbol getSymbol () {
        return null;
    }

}

