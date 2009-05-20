package peripheral.logic.action;

import java.util.Observable;
import peripheral.logic.positioningtool.ActionTool; 
import peripheral.logic.value.UserInput;
import peripheral.logic.value.Value; 

public class RegionRemoveNotInListAction extends RegionAction {

    private Value symbolsToRetain;

    public RegionRemoveNotInListAction () {
    }

    public RegionRemoveNotInListAction (Value symbolsToRetain) {
    }

    public Value getSymbolsToRetain () {
        return symbolsToRetain;
    }

    public java.util.Set<UserInput> getUserInput () {
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
     *        SymbolList.createSymbolList(tool.getSymbols());<br><br>symbolsInRegion.remove(symbolsToRetain);<br><br>for 
     *        (Symbol s : symbolsInRegion.getSymbols()){<br>tool.getSymbols().remove(s);<br>Visualization.remove(s)<br>}
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

}

