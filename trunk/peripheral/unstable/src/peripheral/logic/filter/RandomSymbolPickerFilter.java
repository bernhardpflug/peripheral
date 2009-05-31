/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package peripheral.logic.filter;

import java.util.ArrayList;
import java.util.List;
import peripheral.logic.Logging;
import peripheral.logic.datatype.SymbolList;
import peripheral.logic.positioningtool.PositioningTool;
import peripheral.logic.symboladapter.Symbol;
import peripheral.logic.symboladapter.SymbolAdapter;
import peripheral.logic.value.ConstValue;

/**
 *
 * @author Andi
 */
public class RandomSymbolPickerFilter extends RandomValuePickerFilter {

    public RandomSymbolPickerFilter(SymbolAdapter adapter, String outputVarName) {
        super(adapter, outputVarName);

        FilterInput fi = new FilterInput("symbolList", new Class[]{(new ArrayList<Symbol>()).getClass()});
        filterInputs.put(fi.getName(), fi);

        fi = new FilterInput("nrToPick", new Class[]{Integer.class});
        filterInputs.put(fi.getName(), fi);

        fi = new FilterInput("repeat", new Class[]{Boolean.class});
        filterInputs.put(fi.getName(), fi);

        outputValue = new ConstValue(adapter, outputVarName, new SymbolList(), SymbolList.class);
    }

    @Override
    public void doFilter() {
        List<Symbol> symbolList = (List<Symbol>) getFilterInputValue("symbolList");
        int nrToPick = (Integer) getFilterInputValue("nrToPick");
        boolean repeat = (Boolean) getFilterInputValue("repeat");

        SymbolList pickedSymbols = new SymbolList();

        //if nrToPick >= symbolList.size() and duplicate symbols are not allowed,
        //then all values have to be returned
        if (nrToPick >= symbolList.size() && !repeat) {
            for (Symbol symbol : symbolList){
                pickedSymbols.incNrOfClones(symbol);
            }
        } else {
            int[] pickedIndizes = this.getPickedIndizesNumbers(nrToPick, true, 0, symbolList.size());

            for (int i : pickedIndizes) {
                //pickedValues.add(valueList.get(i));
                pickedSymbols.incNrOfClones(symbolList.get(i));
            }
        }

        outputValue.setValue(pickedSymbols);

        Logging.getLogger().finer("symbolList=" + symbolList + "; nrToPick=" + nrToPick + "; pickedSymbols=" + pickedSymbols);
    }
}
