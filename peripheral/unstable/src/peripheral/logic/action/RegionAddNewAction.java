package peripheral.logic.action;

import java.util.Observable;
import peripheral.logic.Logging;
import peripheral.logic.datatype.SymbolList;
import peripheral.logic.positioningtool.ActionTool;
import peripheral.logic.positioningtool.Region;
import peripheral.logic.symboladapter.Symbol;
import peripheral.logic.symboladapter.SymbolAdapter;
import peripheral.logic.value.UserInput;
import peripheral.logic.value.Value;
import peripheral.logic.Runtime;
import peripheral.logic.symboladapter.ClonedSymbol;
import peripheral.logic.util.PositionRandomizer;
import peripheral.visualization.Visualization;

public class RegionAddNewAction extends RegionAction {

    private Value symbolsToAdd;
    private boolean removeOthers = true;

    public RegionAddNewAction(SymbolAdapter adapter) {
        super(adapter);
    }

    public RegionAddNewAction(SymbolAdapter adapter, Value symbolsToAdd) {
        this(adapter);
        this.symbolsToAdd = symbolsToAdd;
    }

    public java.util.List<UserInput> getUserInput() {
        return null;
    }

    public String getName() {
        return null;
    }

    public String getDescription() {
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
    @Override
    public void execute(ActionTool tool) {
        Visualization viz = Runtime.getInstance().getVisualization();
        Region region = (Region) tool;
        SymbolList symbolsToAdd = getSymbolsToAdd();
        ClonedSymbol clonedSymbol;

        for (Symbol source : region.getSymbolList().getSourceSymbols()) {
            int nrOfClonesInRegion = region.getSymbolList().getNrOfClones(source);
            int nrOfClonesToAdd = symbolsToAdd.getNrOfClones(source);
            int diff = nrOfClonesToAdd - nrOfClonesInRegion;

            if (diff > 0) {
                for (int i = 0; i < diff; i++) {
                    clonedSymbol = region.getSymbolList().addCloneOf(source);
                    clonedSymbol.setPosition(PositionRandomizer.getRandomPosition(region.getBounds()));
                    viz.addSymbol(clonedSymbol, region);

                    Logging.getLogger().finer("added symbol: " + clonedSymbol);
                }
            } else if (diff < 0 && removeOthers) {
                for (int i = 0; i > diff; i--) {
                    clonedSymbol = region.getSymbolList().removeCloneOf(source);
                    viz.removeSymbol(clonedSymbol);

                    Logging.getLogger().finer("removed symbol: " + clonedSymbol);
                }
            }
        }

    }

    public void update(Observable o, Object arg) {
    }

    public SymbolList getSymbolsToAdd() {
        return (SymbolList) symbolsToAdd.getValue();
    }
}

