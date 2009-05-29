/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package peripheral.logic.value;

import peripheral.logic.datatype.Interval;
import peripheral.logic.symboladapter.SymbolAdapter;

/**
 *
 * references a value of the symbol adapter's varpool
 *
 * difference to class VarValue is: this class may only reference
 * a Value in the varpool, which is instance of class SensorValue
 *
 * @author Andi
 */
public class SensorIntervalVarValue extends VarValue {

    public SensorIntervalVarValue(SymbolAdapter adapter, String varName) throws IllegalArgumentException {
        super(adapter, varName);

        if (getReferencedValue() == null) {
            throw new IllegalArgumentException("Cannot reference value '" + varName + "': value is not of type SensorValue.");
        }
    }

    @Override
    public Object getValue() {
        return ((SensorValue)getReferencedValue()).getBounds();
    }

    @Override
    public Class getValueType() {
        return Interval.class;
    }


}
