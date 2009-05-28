package peripheral.logic.value;

import peripheral.logic.symboladapter.SymbolAdapter;


public class VarValue extends Value {

    public VarValue (SymbolAdapter adapter, String varName) {
        super(adapter);
        this.varName = varName;
    }

    public Object getValue () {
        return adapter.getVarpool().get(varName).getValue();
    }

}

