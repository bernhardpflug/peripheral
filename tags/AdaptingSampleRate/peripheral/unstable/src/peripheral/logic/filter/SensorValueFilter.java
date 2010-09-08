/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package peripheral.logic.filter;

import peripheral.logic.symboladapter.SymbolAdapter;
import peripheral.logic.value.ConstValue;
import peripheral.logic.value.SensorValue;

/**
 *
 * @author Andi
 */
public abstract class SensorValueFilter extends Filter {

    protected SensorValue sensorValue;

    public SensorValueFilter (SymbolAdapter adapter, SensorValue sensorValue){
        super(adapter, "");

        this.sensorValue = sensorValue;
        //this.sensorValue.rename(this.sensorValue.getVarName() + "_beforeFiltering");

        FilterInput fi = new FilterInput("applyFilter", new Class[]{Boolean.class});
        filterInputs.put(fi.getName(), fi);

        //outputValue = new ConstValue(adapter, outputVarName, 0, sensorValue.getValueType());
    }

    protected boolean getApplyFilter (){
        return (Boolean) getFilterInputValue("applyFilter");
    }
}