package peripheral.logic.action;

import java.util.Observable;
import peripheral.logic.positioningtool.ActionTool; 
import peripheral.logic.symboladapter.Symbol; 
import peripheral.logic.symboladapter.SymbolAdapter;
import peripheral.logic.value.UserInput;

public abstract class RegionWrapperAction extends RegionAction {

    //private Value symbol;
    private Symbol symbol;

    private SymbolAction symbolAction;

    public RegionWrapperAction (SymbolAdapter adapter, SymbolAction symbolAction, Symbol symbol) {
        super(adapter);
        this.symbolAction = symbolAction;
        this.symbol = symbol;
    }

    public java.util.List<UserInput> getUserInput () {
        return symbolAction.getUserInput();
    }

    @Override
    public String getName (){
        return symbolAction.getName();
    }

    @Override
    public String getDescription (){
        return symbolAction.getDescription();
    }

    @Override
    public void execute (ActionTool tool) {
        symbolAction.execute(symbol);
    }

    public Symbol getSymbol () {
        return null;
    }

}

