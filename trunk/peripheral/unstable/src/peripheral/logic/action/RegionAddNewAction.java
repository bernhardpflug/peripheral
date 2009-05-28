package peripheral.logic.action;

import java.util.Observable;
import peripheral.logic.datatype.SymbolList;
import peripheral.logic.positioningtool.ActionTool; 
import peripheral.logic.symboladapter.SymbolAdapter;
import peripheral.logic.value.UserInput;
import peripheral.logic.value.Value; 

public class RegionAddNewAction extends RegionAction {

    private Value symbolsToAdd;

    public RegionAddNewAction (SymbolAdapter adapter) {
        super(adapter);
    }

    public RegionAddNewAction (SymbolAdapter adapter, Value symbolsToAdd) {
        this(adapter);
        this.symbolsToAdd = symbolsToAdd;
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
     *        SymbolList symbolsInRegion = 
     *        SymbolList.createSymbolList(tool.getSymbols());<br>
     *  <br>//make list of symbols, which should be added, but are already in the 
     *        region<br>symbolsInRegion.intersect(symbolsToAdd);
     *  <br>//remove symbols - from symbols to add - which are already in the region 
     *        symbolsToAdd.Remove(symbolsInRegion);<br><br>for (Symbol s : 
     *        symbolsToAdd.getSymbols()){
     *  <br>  tool.getSymbols().add(s);
     *  <br>  Visualization.add(s)<br>}
     *      </p>
     *      <p style="margin-top: 0">
     *        <br>
     *        
     *      </p>
     *      <p style="margin-top: 0">
     *        
     *      </p>
     *      <p style="margin-top: 0">
     *        <br>
     *      </p>
     */
    public void execute (ActionTool tool) {
    }

    public void update (Observable o, Object arg) {
    }

    public SymbolList getSymbolsToAdd () {
        return null;
    }

}

