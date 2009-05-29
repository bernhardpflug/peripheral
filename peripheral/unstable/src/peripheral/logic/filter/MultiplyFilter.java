/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package peripheral.logic.filter;

import peripheral.logic.Logging;
import peripheral.logic.symboladapter.SymbolAdapter;
import peripheral.logic.value.ConstValue;

/**
 *
 * @author Andi
 */
public class MultiplyFilter extends Filter {

    public MultiplyFilter (SymbolAdapter adapter, String outputVarName){
        super(adapter, outputVarName);

        FilterInput fi = new FilterInput("factor1", new Class[]{Number.class});
        filterInputs.put(fi.getName(), fi);

        fi = new FilterInput("factor2", new Class[]{Number.class});
        filterInputs.put(fi.getName(), fi);

        outputValue = new ConstValue(adapter, outputVarName, 0, Double.class);
    }


    @Override
    public void doFilter () {
        double factor1 = ((Number)getFilterInputValue("factor1")).doubleValue();
        double factor2 = ((Number)getFilterInputValue("factor2")).doubleValue();

        Logging.getLogger().finer("factor1=" + factor1 + "; factor2=" + factor2);

        outputValue.setValue(factor1*factor2);
    }
}
