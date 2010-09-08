/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package peripheral.logic.filter;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import peripheral.logic.Logging;
import peripheral.logic.positioningtool.PositioningTool;
import peripheral.logic.symboladapter.SymbolAdapter;
import peripheral.logic.value.ConstValue;
import peripheral.logic.value.VarValue;

/**
 *
 * @author Andi
 */
public class RandomPositioningToolPickerFilter extends RandomValuePickerFilter {

    public RandomPositioningToolPickerFilter(SymbolAdapter adapter, String outputVarName) {
        super(adapter, outputVarName);

        List<PositioningTool> value = new ArrayList<PositioningTool>();

        FilterInput fi = new FilterInput("valueList", new Class[]{value.getClass()});
        filterInputs.put(fi.getName(), fi);

        fi = new FilterInput("nrToPick", new Class[]{Integer.class});
        filterInputs.put(fi.getName(), fi);

        outputValue = new ConstValue(adapter, outputVarName, value, value.getClass());
    }

    @Override
    public void doFilter() {
        List<PositioningTool> valueList = (List<PositioningTool>) getFilterInputValue("valueList");
        int nrToPick = (Integer) getFilterInputValue("nrToPick");

        List<PositioningTool> pickedValues;

        //nrToPick = 2;

        //if nrToPick >= valueList.size() then all values have to
        //be returned
        if (nrToPick >= valueList.size()) {
            pickedValues = new ArrayList<PositioningTool>(valueList);
        } else {
            pickedValues = new ArrayList<PositioningTool>();

            int[] pickedIndizes = this.getPickedIndizesNumbers(nrToPick, false, 0, valueList.size());

            for (int i : pickedIndizes) {
                pickedValues.add(valueList.get(i));
            }
        }

        outputValue.setValue(pickedValues);

        Logging.getLogger().finer("valueList=" + valueList + "; nrToPick=" + nrToPick + "; pickedValues=" + pickedValues);
    }

}
